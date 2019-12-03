(ns ms-web.views
  (:require [ms-web.events :as events]
            [ms-web.subs :as subs]
            [re-frame.core :as re-frame]
            [reagent.core :as r]
            ["react-transition-group" :as tg]
            ["semantic-ui-react" :as ui]
            ["react-twitter-embed" :as twitter]))

(defn footer []
  [:footer
   "Copyright 2019-2024 Nagoya University Sumo Club. All Rights Reserved."])

(defn contents-wrapper [children]
  [:div
   [:> ui/Header {:as "h1" :className "contentsHeader"}]
   [:> ui/Container {:className "contentsInner"}
    [:> ui/Grid
     [:> ui/Grid.Column {:mobile 16 :computer 11 :style {:whiteSpace "pre-line"}}
      children]
     [:> ui/Grid.Column {:mobile 16 :computer 5}
      [:> ui/Responsive {:minWidth 1201}
       [:> ui/Segment {:basic true}
        [:h2 "新着情報（Twitter）"]
        [:> ui/Segment {:basic true}
         [:> twitter/TwitterTimelineEmbed {:sourceType "profile" :userId 1922508294
                                           :options {:height 550}}]]]]]]]
   [footer]])

(defn home-panel []
  [:div
   [:> ui/Header {:as "div" :className "topHeader"}
    [:> ui/Responsive {:minWidth 1201}
     [:h1 "名古屋大学相撲部"]]]
   [:> ui/Segment {:basic true :textAlign "center"}
    [:a {:href "https://twitter.com/nu_sumo"}
     [:> ui/Icon {:name "twitter" :style {:color "#1EA1F2"} :size "big"}]]
    [:a {:href "https://www.facebook.com/NUSUMOCLUB/"}
     [:> ui/Icon {:name "facebook" :style {:color "#4267B2"} :size "big"}]]
    [:a {:href "https://www.instagram.com/nu_sumosumo/"}
     [:> ui/Icon {:name "instagram" :style {:color "#D12798"} :size "big"}]]]
   [:> ui/Container
    [:> ui/Grid
     [:> ui/Grid.Column {:mobile 16 :computer 10 :style {:padding 0}}
      [:> ui/Segment {:basic true}
       [:h2 "新入部員募集中！！"]
       [:> ui/Card {:style {:width "100%"}}
        [:> ui/Image {:src "/img/bosyu.jpg"}]]
       [:> ui/Segment {:basic true :style {:whiteSpace "pre-line"}}
        "名大相撲部では新入生に限らず、常に新入部員を募集してます。"
        "相撲が好き。相撲が嫌い。何か格闘技がやりたい。大学がつまらない。大学に誇りを感じたい。とにかく面白いことがしたい...。"
        "どんな人も歓迎します。\n"
        "殆どの現役部員、OB/OGがそうであったように未経験者大歓迎です。\n"
        "入部希望者は" [:a {:href "#/inquiry"} "連絡先"] "までご連絡いただくか、" [:a {:href "#/schedule"} "稽古日に直接道場"] "を訪ねて下さい。"]]

      [:> ui/Segment {:basic true}
       [:h2 "第５８回全国七大学総合体育大会相撲競技団体優勝"]
       [:> ui/Card {:style {:width "100%"}}
        [:> ui/Image {:src "/img/shichiteisen.jpg"}]]
       [:> ui/Segment {:basic true :style {:whiteSpace "pre-line"}}
        "皆様の応援のお陰で名大相撲部は第５８回全国七大学総合体育大会相撲部の部で七年ぶりの団体優勝を果たしました。"
        "かつて七連覇を達成した当部としては、これを古豪復活の狼煙として来年度以降の活躍に繋げたいと思います。"]]

      [:> ui/Segment {:basic true}
       [:h2 "相撲部土俵改修プロジェクト"]
       [:> ui/Card {:style {:width "100%"}}
        [:> ui/Image {:src "/img/dohyo.jpg"}]]
       [:> ui/Segment {:basic true :style {:whiteSpace "pre-line"}}
        "細谷師範の還暦を記念し、 名大相撲部の土俵改修プロジェクトを始動します。\n"
        "相撲部創部以来、大切に作り使ってきた道場・土俵を今後後輩たちに引き継いで行くために、改修費用のクラウドファンディングを行います。"
        "ご賛同していただける方は下記ページより応募をお願いいたします。\n\n"
        [:a {:href "https://polca.jp/projects/MFaewo6Nl8I?fbclid=IwAR1aM09Zxhaxv4DfVSqHXSylX1ZP6i89wOot7QZ4iGc2Brz1rh3owa-10YY"}
         "名大相撲部土俵改修費用クラウドファンディング"]]]]
     [:> ui/Grid.Column {:mobile 16 :computer 6}
      [:> ui/Segment {:basic true}
       [:h2 "新着情報（Twitter）"]
       [:> ui/Segment {:basic true}
        [:> twitter/TwitterTimelineEmbed {:sourceType "profile" :userId 1922508294
                                          :options {:height 500}}]]]]]]
   [footer]])

(def grades {"grade4" {:name "四回生" :order 0}
             "grade3" {:name "三回生" :order 1}
             "grade2" {:name "二回生" :order 2}
             "grade1" {:name "一回生" :order 3}
             "managers" {:name "首脳陣" :order 4}
             "obg2017" {:name "2017年度卒業生" :order 5}
             "obg2016" {:name "2016年度卒業生" :order 6}
             "obg2015" {:name "2015年度卒業生" :order 7}
             "obg2014" {:name "2014年度卒業生" :order 8}
             "obg2013" {:name "2013年度卒業生" :order 9}
             "obg2012" {:name "2012年度卒業生" :order 10}
             "obg2011" {:name "2011年度卒業生" :order 11}
             "obg2010" {:name "2010年度卒業生" :order 12}})

(defn member-panel []
  [contents-wrapper
   (let [members @(re-frame/subscribe [::subs/members])
         grade-filter @(re-frame/subscribe [::subs/url-params])
         active-panel @(re-frame/subscribe [::subs/active-panel])]
     [:<>
      [:h2 "部員名簿"]
      [:> ui/Grid
       (for [[grade members] (cond->> members
                               (not= grade-filter "all") (filter #(= (:member/grade-id %) grade-filter))
                               true (group-by :member/grade-id)
                               true (sort-by #(:order (get grades (first %)))))]
         ^{:key grade}
         [:<>
          [:> ui/Grid.Column {:mobile 16 :computer 16}
           [:h3 (:name (get grades grade))]]
          (for [{:keys [member/id member/name member/image member/introduction1 member/introduction2
                        member/introduction3]} members]
            ^{:key id}
            [:> ui/Grid.Column {:mobile 16 :computer 8}
             [:> ui/Card {:style {:whiteSpace "pre-line" :width "100%"}}
              (when image
                [:> ui/Image {:src (str "/img/member/" image) :fluid true}])
              [:> ui/Card.Content
               [:> ui/Card.Header name]
               [:> ui/Card.Meta introduction1]
               (when introduction2 [:> ui/Card.Description introduction2])
               (when introduction3 [:> ui/Card.Description introduction3])]]])])]])])

(defn schedule-panel []
  [contents-wrapper
   (let [schedule @(re-frame/subscribe [::subs/schedule])
         keiko @(re-frame/subscribe [::subs/keiko])]
     [:<>
      [:h2 "年間予定"]
      [:table
       [:tbody
        (for [s schedule]
          ^{:key (:date s)}
          [:tr {:style {:textAlign "left"}}
           [:th (:date s)]
           [:td (:event s)]])]]
      [:h2 "稽古"]
      "下記の曜日に行ってます。"
      [:ul
       (for [k keiko]
         ^{:key k}
         [:li k])]
      [:h3 "稽古場所"]
      [:p "名古屋大学相撲部道場"]
      [:p "武道場の前です。"]
      [:iframe {:src "https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d203.88139249549874!2d136.9613073963299!3d35.1540698603814!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sja!2sjp!4v1575028143950!5m2!1sja!2sjp"
                :frameBorder 0
                :style {:border 0
                        :width "100%"
                        :height "300px"}
                :allowFullScreen true}]])])

(defn record-panel []
  [contents-wrapper
   [:<>
    [:h2 "This is the Record Page."]]])

(defn inquiry-panel []
  [contents-wrapper
   [:<>
    [:h2 "連絡先"]
    [:p "名古屋大学相撲部に関するお問い合わせは下記のアドレスまでお願いします。"]
    [:> ui/Image {:src "/img/mail.jpg" :style {:maxWidth "500px"}}]
    [:br]
    [:p "（スパム対策のためアドレスは画像で表示させてあります。）"]]])

(defn declation-panel []
  [contents-wrapper
   [:<>
    [:h2 "また名大相撲部宣言"]
    [:p "我ら名古屋大学体育会相撲部は、経済と情報の急激なグローバリゼーションの進行する今日、人類社会の福祉の向上、国際間の平和、地球環境の保全をその使命とし、2000年4月、日本国愛知県名古屋市に設立された。"]
    [:p "我ら名古屋大学体育会相撲部は、地球というこの天体の地表で文化と経済のダイナミズム維持に貢献するために、日本列島が育んできた歴史を継承発展させることをその理想とし、相撲という文化の復興を目論見、研鑽を続けている。"]
    [:p "我ら名古屋大学体育会相撲部は、大量生産、大量消費の際限のない増殖から、循環経済への移行期にあり、物質によらない繁栄の一つの形式の発露として、相撲という文化を通じて遊び通すことに全力を傾注する。"]
    [:p "我ら名古屋大学体育会相撲部は、簡便であることや、コストパフォーマンスを尊ぶ風潮を下品であると断罪し、不必要なまでに格好をつけることに拘り続ける。"]
    [:p "我ら名古屋大学体育会相撲部は、「五層櫓のてっぺんに金のシャチホコ雨ざらし、ああこりゃこりゃ。」の精神を胸に秘め、日々偏見や陋習と戦い続ける。"]
    [:p "我ら名古屋大学体育会相撲部は、何をおいても歌舞き、槍が降ろうが、生首が飛ぼうが、歌舞き続けることをここに宣言する、がや。"]]])

(defn freshman-panel []
  [contents-wrapper
   [:<>
    [:h2 "新入部員に向けて"]
    [:h3 "『太らずして勝つ』、『人生とはネタである』"]
    "この二つは我々名大相撲部の座右の銘である。日本の国技である相撲。それは天皇の御前でも行われる神聖な武道であり、その歴史は奈良時代以前にまで遡り、時と共に少しずつ形を変えながら現在へ至っている。そんな国技相撲に対して諸君が持っている印象は如何なるものであろうか？\n\n"
    "廻しを巻くのが恥ずかしい？確かにそうかもしれない。しかし、最初は恥ずかしがっていた人が、時が経つと平気で人前で巻くようになるのだ。これは一体どういうことであろうか？そう、『慣れ』である。我々人類にはかくもありがたい機能が備わっているのだ。無論、君にも。\n\n"
    "体が小さいから駄目？普通ならそうかもしれない。だが我々は違う。むしろ日々の稽古によって鍛えられた部員たちは、いわゆる『相撲取り体型』ではなく『やせマッチョ』ばかりである。意外に思うかもしれないが、相撲は他の格闘技ほど対格差によるハンディキャップがない。事実、７０kgのある部員が国技館の土俵で、１２０kgの巨漢を投げ転がしている。勿論、体格が大きい方が有利なのに違いはないが、これだけの体重差の相手とも張り合える格闘技は相撲以外にないのではないか。\n\n"
    "とはいえ我々は決して弱いわけではない。戦績を見て頂ければ分かるとおり、華々しい結果を残している。しかし、我が部には小学校から高校まで相撲に打ち込んできたという相撲キャリアは一人もいない。皆、大学から相撲を始めた者ばかりである。それでも日々論理的な組み立てをした稽古を積み、日々精進している。\n\n"
    "また、我が部が目指す者は強さだけではない。ノリの良さ、そしてかっこよさである。名大相撲部が主催する学内相撲大会には毎年１００人近い名大生が参加し、盛り上がりをみせている。また、部員たちによって企画された主催のイベントでは過去二回、名古屋最大のクラブであるOZONにいずれも１０００人を動員し、名古屋の夜に激震を走らせた。更に０６年からは４年連続で、名古屋城を貸し切って『どすこい！！名古屋城RAVE』を主催し、毎年２０００人を越える人々が集まり真夏の夜の夢を楽しんだ。また、秋にはJazzライブと相撲大会の融合により地域活性化に取り組んでいる。\n\n"
    "イベントだけではない。夏合宿では鳥羽の離島で一週間相撲漬け。さらには遠征の帰りには温泉、グルメツアーを強行するなど、他の部活にはできない、いや日本中どこを探してもこんな貴重な経験ができる部活は存在しない。名大相撲部でしか体験できないことがここにはある。\n\n"
    "さて、ここまで長々と書いてきたが、結局のところ言いたいことはただひとつ、\n\n"
    [:strong "『名大生の諸君、悪いことは言わない、名大相撲部に入り給え！！』"]]])

(defn ob-panel []
  [contents-wrapper
   [:<>
    [:h2 "OBの就職先"]
    [:h3 "あなたには履歴書に書ける「自己PR」がありますか？"]
    "就職氷河期で買い手市場となった今、旧帝大卒だからといって簡単に内定が貰える訳ではありません。" [:br]
    "しかし、不況にも関わらず体育会の学生が大手企業に就職しているのは、紛れも無い事実です。" [:br] [:br]
    "履歴書（ES）や面接では主に「志望動機」「研究内容」「自己PR」を聞かれますが、企業が大手であればあるほど「志望動機」や「研究内容」よりも「自己PR」を重視する傾向があります。" [:br]
    "「自己PRをして下さい」「あなたの強みは何ですか」「学生時代に頑張ったことは何ですか」と聞かれた時に、アルバイトやサークルの話ではなく部活動の話をすることにより、満足のいく就職活動を終えることができるよう、心からお祈り申し上げます。" [:br]
    [:h3 "主な就職先（五十音順）"]
    [:ul
     [:li "愛知県庁"]
     [:li "アクセンチュア"]
     [:li "旭化成"]
     [:li "朝日新聞社"]
     [:li "味の素"]
     [:li "伊藤忠商事"]
     [:li "NTTドコモ"]
     [:li "大垣共立銀行"]
     [:li "岡崎信用金庫"]
     [:li "鹿島建設"]
     [:li "キリンビール"]
     [:li "国際協力銀行"]
     [:li "サントリー"]
     [:li "新日本製鐵"]
     [:li "中日新聞社"]
     [:li "中部電力"]
     [:li "電源開発"]
     [:li "電通"]
     [:li "デンソー"]
     [:li "東海旅客鉄道"]
     [:li "トヨタ自動車"]
     [:li "豊田自動織機"]
     [:li "豊田通商"]
     [:li "名古屋市役所"]
     [:li "名古屋市消防局"]
     [:li "名古屋鉄道"]
     [:li "日本ガイシ"]
     [:li "日本相撲協会"]
     [:li "日本たばこ産業"]
     [:li "博報堂"]
     [:li "三井住友銀行"]
     [:li "三菱重工業"]
     [:li "三菱UFJ銀行"]
     [:li "リクルート"]]
    "などなど。"]])

(defn keiko-panel []
  [contents-wrapper
   [:<>
    [:h2 "稽古"]
    [:> ui/Card {:style {:width "100%" :maxWidth "500px"}}
     [:> ui/Image {:src "/img/keiko.jpg"}]]
    [:ul
     [:li "蹲踞礼"]
     [:li "準備運動"]
     [:li "四股八十八回"]
     [:li "摺り足"]
     [:li "押し"]
     [:li "技術練"]
     [:li "三番"]
     [:li "ぶつかり"]
     [:li "蹲踞礼"]
     [:li "柔軟"]
     [:li "掃除、風呂、ちゃんこ"]]]])

(defn chanko-panel []
  [contents-wrapper
   [:<>
    [:h2 "名大相撲部ちゃんこ"]
    [:> ui/Card {:style {:width "100%" :maxWidth "500px"}}
     [:> ui/Image {:src "/img/chanko.jpg"}]]
    [:h3 "材料"]
    [:ul
     [:li "昆布"]
     [:li "鰹だし(顆粒)"]
     [:li "鶏手羽先"]
     [:li "豚こま切れ"]
     [:li "鶏もも肉(むね肉はNG)"]
     [:li "鶏レバー"]
     [:li "白菜orキャベツ(安いほう）"]
     [:li "大根"]
     [:li "ニンジン"]
     [:li "ゴボウ"]
     [:li "しいたけ"]
     [:li "こんにゃく"]
     [:li "油揚げ"]
     [:li "豆腐"]
     [:li "水菜"]
     [:li "その他季節に応じて。"]]
    [:h3 "作り方"]
    [:ul
     [:li "材料を適当な大きさに切り、鶏レバーの血抜き、こんにゃくと油揚げの湯通しをする。"]
     [:li "大きな鍋にたっぷりの水と少量の酒を入れ、昆布、鶏手羽先、大根、ニンジン、ゴボウを加えて火を点ける。"]
     [:li "沸騰したら、鶏もも肉、鶏レバー、しいたけ、こんにゃく、油揚げ、白菜、豚こま切れ、を順に加える。"]
     [:li "アクを取り、鰹だし、塩、しょうゆで味を調える。"]
     [:li "火を止め、豆腐、水菜を加えて出来上がり。"]]]])

(defn ibukioroshi-panel []
  [contents-wrapper
   [:<>
    [:h2 "第八高等学校寮歌 伊吹おろし"]
    [:h3 "前口上"]
    "富貴名門の子女に恋するを純情の恋と誰が云ふ" [:br]
    "路頭にさまよへる女に恋するを不浄の恋と誰が云ふ" [:br]
    "泣いて笑って月下の酒場にて媚を売る女の中にも水蓮の如き純情あり" [:br]
    "酒は飲むべし百薬の長　女は抱くべし陶酔の境" [:br]
    "女の膝枕にて快楽の一夜を過ごさば　人生の夢もありなばまた恋もありなん" [:br]
    "雨降らば触れ　風吹かば吹け　いざ進み湯かむかな若き男の子よ" [:br]
    "安危すごめく混乱の巷　いざ声高らかに歌わむかな" [:br]
    "第八高等学校寮歌　伊吹おろし" [:br]
    [:br]
    [:p "一、 伊吹おろしの雪消えて   木曽の流れに囁けば"]
    [:p "        光に満てる国原の   春永劫(えいごう)に薫るかな"]
    [:p "二、 夕日あふれて草萌ゆる   瑞穂丘に佇めば"]
    [:p "        零(こぼ)れ地に咲く花菜にも   うら若き子は涙する"]
    [:p "三、 見よソロモンの栄耀も   野の白百合に及(し)かざるを"]
    [:p "        路傍の花にゆき暮れて   果てなき夢の姿かな"]
    [:p "四、 花に滴る日の水沫(みずわ)   命の啓示(さとし)を語るとき"]
    [:p "        希望(のぞみ)に滾(た)ぎる若き頬を   はるかに星は照すなり"]
    [:p "五、 神秘の闇のおとずれに   いつしか寮の灯火(ともしび)は"]
    [:p "        瞬き(またたき)そめて我を待つ   地上の夢よいざ去らば"]
    [:p "六、 杳(よう)靄(あい)融(と)けし丘の上に   いづくともなく春をよぶ"]
    [:p "        歌やすらかに流れ来る   紺青(こんじょう)の月影濃けれ"]
    [:> ui/Card {:style {:width "100%" :maxWidth "500px"}}
     [:> ui/Image {:src "/img/ibukioroshi.jpg"}]]]])

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :member-panel [] [member-panel])
(defmethod panels :schedule-panel [] [schedule-panel])
(defmethod panels :record-panel [] [record-panel])
(defmethod panels :inquiry-panel [] [inquiry-panel])
(defmethod panels :declation-panel [] [declation-panel])
(defmethod panels :freshman-panel [] [freshman-panel])
(defmethod panels :ob-panel [] [ob-panel])
(defmethod panels :keiko-panel [] [keiko-panel])
(defmethod panels :chanko-panel [] [chanko-panel])
(defmethod panels :ibukioroshi-panel [] [ibukioroshi-panel])
(defmethod panels :default [] [:div])

(defn show-panel [panel-name]
  [panels panel-name])

(def menu [{:name "ホーム" :key "home" :icon "home"}
           {:name "名大相撲部" :key "about" :icon "info"
            :sub-menu [{:key "declation" :name "また名大相撲部宣言"}
                       {:key "freshman" :name "新入部員に向けて"}
                       {:key "ob" :name "OBの就職先"}
                       {:key "keiko" :name "稽古詳細"}
                       {:key "chanko" :name "ちゃんこ"}
                       {:key "ibukioroshi" :name "第八高等学校寮歌 伊吹おろし"}]}
           {:name "部員名簿" :key "member" :icon "users"
            :sub-menu [{:key "grade4" :name "四回生"}
                       {:key "grade3" :name "三回生"}
                       {:key "grade2" :name "二回生"}
                       {:key "grade1" :name "一回生"}
                       {:key "managers" :name "首脳陣"}
                       {:key "obg2017" :name "2017年度卒業生"}
                       {:key "obg2016" :name "2016年度卒業生"}
                       {:key "obg2015" :name "2015年度卒業生"}
                       {:key "obg2014" :name "2014年度卒業生"}
                       {:key "obg2013" :name "2013年度卒業生"}
                       {:key "obg2012" :name "2012年度卒業生"}
                       {:key "obg2011" :name "2011年度卒業生"}
                       {:key "obg2010" :name "2010年度卒業生"}
                       {:key "all" :name "全て"}]}
           {:name "稽古・年間予定" :key "schedule" :icon "calendar"}
           #_{:name "戦績" :key "record" :icon "book"}
           {:name "問い合わせ" :key "inquiry" :icon "mail"}])

(defn pc-container []
  (let [active-panel @(re-frame/subscribe [::subs/active-panel])]
    [:> ui/Container {:className "mainContainer"}
     [:> ui/Menu {:className "mainMenu" :secondary true :fixed "top" :size "large"}
      (for [{:keys [key name icon sub-menu] :as item} menu
            :let [panel (keyword (str key "-panel"))]]
        (if sub-menu
          ^{:key key}
          [:> ui/Dropdown {:text name :pointing true :className "link item"}
           [:> ui/Dropdown.Menu
            (for [item sub-menu]
              ^{:key (:key item)}
              [:> ui/Dropdown.Item {:text (:name item)
                                    :as "a" :href (str "#/" key "/" (:key item))}])]]
          ^{:key key}
          [:> ui/Menu.Item {:as "a" :href (str "#/" key)
                            :active (= active-panel panel)}
           [:> ui/Icon {:name icon}] name]))]
     [:> tg/TransitionGroup {:id "contents" :className "transition"}
      [:> tg/CSSTransition {:key active-panel :classNames "transition" :timeout 300}
       [show-panel active-panel]]]]))

(defn mobile-container []
  (let [active-panel @(re-frame/subscribe [::subs/active-panel])
        sub-menu @(re-frame/subscribe [::subs/sub-menu])
        menu-open? @(re-frame/subscribe [::subs/menu-open?])
        parent @(re-frame/subscribe [::subs/parent])]
    [:> ui/Container {:className "mainContainer" :style {:height "100%"}}
     [:> ui/Sidebar.Pushable {:style {:height "100%"}}
      [:> ui/Sidebar {:as ui/Menu
                      :className "mainMenu"
                      :animation "push"
                      :icon "labeled"
                      :vertical true
                      :visible menu-open?
                      :width "thin"
                      :style {:height "100%"}}
       (for [{:keys [key name icon sub-menu] :as item} menu
             :let [panel (keyword (str key "-panel"))]]
         ^{:key key}
         [:> ui/Menu.Item (if sub-menu
                            {:active (= active-panel panel)
                             :onClick #(re-frame/dispatch [::events/set-sub-menu key sub-menu])}
                            {:as "a"
                             :active (= active-panel panel)
                             :href (str "#/" key)})
          [:> ui/Icon {:name icon}]
          name])]
      [:> ui/Sidebar.Pusher {:style {:height "100%"}}
       [:> ui/Segment {:basic true :style {:min-height "100vh" :padding 0 :height "100%"}}
        [:> ui/Sidebar.Pushable
         [:> ui/Sidebar {:as ui/Menu
                         :className "subMenu"
                         :animation "push"
                         :icon "labeled"
                         :vertical true
                         :visible (some? sub-menu)
                         :width "thin"
                         :style {:height "100%"}}
          (for [{:keys [key name icon] :as item} sub-menu
                :let [panel (keyword (str key "-panel"))]]
            ^{:key key}
            [:> ui/Menu.Item {:as "a"
                              :active (= active-panel panel)
                              :href (str "#/" parent "/" key)}
             name])]
         [:> ui/Sidebar.Pusher {:style {:height "100%"}}
          [:> ui/Segment {:basic true :style {:min-height "100vh" :padding 0 :height "100%"}}
           [:> ui/Menu {:fixed "top" :className "noStyle mobileMenu"}
            [:div.menuButtonWrapper
             [:a {:className (str "menuButton" (when menu-open? " active"))
                  :onClick #(do
                              (re-frame/dispatch [::events/toggl-menu-open])
                              (when sub-menu
                                (re-frame/dispatch [::events/set-sub-menu nil])))}
              [:span]
              [:span]
              [:span]]]
            [:div
             [:h1 [:a {:href "#/"} "名古屋大学相撲部"]]]]
           [:> tg/TransitionGroup {:id "contents" :className "transition"
                                   :style {:height "100%"}}
            [:> tg/CSSTransition {:key active-panel :classNames "transition" :timeout 300
                                  :style {:height "100%"}}
             [show-panel active-panel]]]]]]]]]]))

(defn main-container []
  [:<>
   [:> ui/Responsive {:minWidth 1201}
    [pc-container]]
   [:> ui/Responsive {:maxWidth 1200}
    [mobile-container]]])
