(ns ms-web.views.panels
  (:require [ms-web.events :as events]
            [ms-web.subs :as subs]
            [re-frame.core :as re-frame]
            [reagent.core :as r]
            ["semantic-ui-react" :as ui]
            ["react-twitter-embed" :as twitter]
            ["react-pdf" :as pdf]))

(defn image
  [{:keys [image-path alt url]}]
  (let [image (new js/Image)]
    (aset image "src" image-path)
    (let [load (r/atom nil)]
      (aset image "onload" (fn [o] (reset! load o)))
      (fn []
        [:> ui/Card {:className "image" :style {:width "100%"}}
         (if (nil? @load)
           [:> ui/Segment {:className "imageLoader" :basic true}
            [:> ui/Dimmer {:active true}
             [:> ui/Loader]]]
           (if url
             [:a {:href url} [:img {:src image-path :className "ui image" :alt alt}]]
             [:img {:src image-path :className "ui image" :alt alt}]))]))))

(defn home-panel []
  (let [modal-content (r/atom nil)]
    (fn []
      [:<>
       (when-let [{:keys [title pdf]} @modal-content]
         [:<>
          [:> ui/Responsive {:minWidth 1201}
           [:> ui/Modal {:open (some? @modal-content)
                         :onClose #(reset! modal-content nil)
                         :style {:width "750px"}}
            [:> ui/Modal.Header title]
            [:> ui/Modal.Content
             [:> pdf/Document {:file pdf}
              [:> pdf/Page {:pageNumber 1 :width 700}]]]]]
          [:> ui/Responsive {:maxWidth 1200}
           [:> ui/Modal {:open (some? @modal-content)
                         :onClose #(reset! modal-content nil)
                         :style {:width "350px"}}
            [:> ui/Modal.Header title]
            [:> ui/Modal.Content
             [:> pdf/Document {:file pdf}
              [:> pdf/Page {:pageNumber 1 :width 300}]]]]]])
       [:div {:style {:margin-bottom "25px"}}
        [:h2 "新型コロナウィルスの影響と今後の活動につきまして"]
        [:> ui/Segment {:basic true :style {:whiteSpace "pre-line"}}
         "新型コロナウィルスの影響により現在活動を自粛しております。最新の活動状況についてはTwitterをご確認下さい。"]]
       [:div {:style {:margin-bottom "25px"}}
        [:h2 "2020年新歓ビラ"]
        [:> ui/Segment {:basic true :style {:whiteSpace "pre-line"}}
         [:p "今年の新歓用ビラを公開します。"]
         [:> ui/Grid
          [:> ui/Grid.Column {:mobile 16 :computer 16
                              :onClick #(reset! modal-content
                                                {:title "新入部員募集中"
                                                 :pdf "/img/pamphlet_2020.pdf"})}
           [image {:image-path "/img/pamphlet_2020.jpg" :alt "新入部員募集中"}]]
          #_[:> ui/Grid.Column {:mobile 16 :computer 8
                                :onClick #(reset! modal-content
                                                  {:title "新入部員募集中"
                                                   :pdf "/img/pamphlet_2020_reverse.pdf"})}
             [image {:image-path "/img/pamphlet_2020_reverse.jpg" :alt "新入部員募集中"}]]]]]

       #_[:div {:style {:margin-bottom "25px"}}
          [:h2 [:> ui/Icon {:name "calendar alternate outline" :style {:margin-right "5px"}}]
           "2020年新入生歓迎イベント予定追加"]
          [:> ui/Segment {:basic true :style {:whiteSpace "pre-line"}}
           [:a {:href "/schedule"} "稽古・年間予定"] " に今年の新入生歓迎イベント予定を追加しました。"
           "今年の新入生以外でも歓迎しますので、是非ご参加下さい。"
           "各イベントの詳細はTwitterにて報知していきます。"]]
       [:div {:style {:margin-bottom "25px"}}
        [:h2  "新入部員募集中！！"]
        [image {:image-path "/img/bosyu.jpg" :alt "新入部員募集中"}]
        [:> ui/Segment {:basic true :style {:whiteSpace "pre-line"}}
         "名大相撲部では新入生に限らず、常に新入部員を募集してます。"
         "相撲が好き。相撲が嫌い。何か格闘技がやりたい。大学がつまらない。大学に誇りを感じたい。とにかく面白いことがしたい...。"
         "どんな人も歓迎します。\n"
         "殆どの現役部員、OB/OGがそうであったように未経験者大歓迎です。\n"
         "入部希望者は" [:a {:href "/inquiry"} "連絡先"] "までご連絡いただくか、" [:a {:href "/schedule"} "稽古日に直接道場"] "を訪ねて下さい。"]]
       [:div {:style {:margin-bottom "25px"}}
        [:h2 "第５８回全国七大学総合体育大会相撲競技団体優勝"]
        [image {:image-path "/img/shichiteisen.jpg" :alt "第５８回全国七大学総合体育大会相撲競技団体優勝"}]
        [:> ui/Segment {:basic true :style {:whiteSpace "pre-line"}}
         "皆様の応援のお陰で名大相撲部は第５８回全国七大学総合体育大会相撲部の部で七年ぶりの団体優勝を果たしました。"
         "かつて七連覇を達成した当部としては、これを古豪復活の狼煙として来年度以降の活躍に繋げたいと思います。"]]
       [:div {:style {:margin-bottom "25px"}}
        [:h2 "合宿紹介"]
        [image {:image-path "/img/gassyuku.jpg" :alt "合宿紹介"}]
        [:> ui/Segment {:basic true :style {:whiteSpace "pre-line"}}
         "入部を検討している方向けに相撲部での生活がイメージできるように、2019年の合宿の様子をまとめました。"
         "合宿は部員同士は元より、他大学とも交流し絆を深める場です。\n"
         "2019年は北海道、三重県で３つの合宿を行いました。"
         [:br]
         [:> ui/List {:bulleted true}
          [:> ui/List.Item [:a {:href "/monbetsu-gassyuku-2019"} "紋別合宿"]]
          [:> ui/List.Item [:a {:href "/ozoracho-gassyuku-2019"} "大空町合宿"]]
          [:> ui/List.Item [:a {:href "/toshijima-gassyuku-2019"} "答志島合宿"]]]]]])))

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

(defn member-card [member]
  (let [{:keys [member/id member/name member/image member/introduction1 member/introduction2
                member/introduction3]} member
        image-path (when image (str "/img/member/" image))
        image      (new js/Image)]
    (aset image "src" image-path)
    (let [load (r/atom nil)]
      (aset image "onload" (fn [o] (reset! load o)))
      (fn []
        [:> ui/Grid.Column {:mobile 16 :computer 8}
         [:> ui/Card {:style {:whiteSpace "pre-line" :width "100%"}}
          (when image-path
            (if (nil? @load)
              [:> ui/Segment {:basic true :style {:margin 0 :height "258px"}}
               [:> ui/Dimmer {:active true}
                [:> ui/Loader]]]
              [:img {:src image-path :className "ui fluid image" :alt name}]))
          [:> ui/Card.Content
           [:> ui/Card.Header name]
           [:> ui/Card.Meta introduction1]
           (when introduction2 [:> ui/Card.Description introduction2])
           (when introduction3 [:> ui/Card.Description introduction3])]]]))))

(defn member-panel []
  (let [members @(re-frame/subscribe [::subs/members])
        grade-filter @(re-frame/subscribe [::subs/url-params])
        active-panel @(re-frame/subscribe [::subs/active-panel])]
    [:<>
     [:h2 "部員名簿"]
     (for [[grade members] (cond->> members
                             (not= grade-filter "all") (filter #(= (:member/grade-id %) grade-filter))
                             true (group-by :member/grade-id)
                             true (sort-by #(:order (get grades (first %)))))]
       ^{:key grade}
       [:> ui/Grid
        [:> ui/Grid.Column {:mobile 16 :computer 16}
         [:h3 (:name (get grades grade))]]
        (for [member  members]
          ^{:key (:member/id member)}
          [member-card member])])]))

(defn schedule-panel []
  (let [modal-content (r/atom nil)]
    (fn []
      (let [schedule @(re-frame/subscribe [::subs/schedule])
            keiko @(re-frame/subscribe [::subs/keiko])]
        [:<>
         (when-let [{:keys [event tweet-id]} @modal-content]
           [:> ui/Modal {:open (some? @modal-content) :size "tiny"
                         :onClose #(reset! modal-content nil)}
            [:> ui/Modal.Header event]
            [:> ui/Modal.Content
             [:> twitter/TwitterTweetEmbed
              {:tweetId tweet-id}]]])
         [:h2 "稽古"]
         "コロナウィルスの影響により現在活動を自粛しております。最新の活動状況についてはTwitterをご確認下さい。"
         #_"下記の曜日に行ってます。"
         #_[:> ui/List {:bulleted true}
            (for [k keiko]
              ^{:key k}
              [:> ui/List.Item k])]
         [:h3 "稽古場所"]
         [:p "名古屋大学相撲部道場"]
         #_[:p "名古屋大学東山キャンパス北西端、体育館と武道場の間にあります。見学はいつでも受け付けていますので、気軽に道場を訪ねて下さい。"]
         [:p "名古屋大学東山キャンパス北西端、体育館と武道場の間にあります。"]
         [:iframe {:src "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3262.103562152197!2d136.95911871514335!3d35.15403788032058!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x60037b809397c643%3A0x2b0022cf00051e20!2z55u45pKy5aC0!5e0!3m2!1sja!2sjp!4v1578043838702!5m2!1sja!2sjp"
                   :frameBorder 0
                   :style {:border 0
                           :width "100%"
                           :height "300px"}
                   :allowFullScreen true}]
         [:h2 "年間予定(2020)"]
         [:p "※コロナウィルスの影響により現在活動を自粛しております。最新の活動状況についてはTwitterをご確認下さい。"]
         [:> ui/Table {:celled true}
          [:> ui/Table.Body
           (for [{:keys [date event link tweet-id] :as s} (filter #(= "2020" (:year %))  schedule)]
             ^{:key date}
             [:> ui/Table.Row {:style {:textAlign "left"}}
              [:> ui/Table.Cell date]
              [:> ui/Table.Cell
               (cond
                 (some? link)
                 [:a {:href link
                      :style {:cursor "pointer"}} event]
                 (some? tweet-id)
                 [:a {:onClick #(reset! modal-content s)
                      :style {:cursor "pointer"}} event]
                 :else
                 event)]])]]
         [:h2 "年間予定(2019)"]
         [:> ui/Table {:celled true}
          [:> ui/Table.Body
           (for [{:keys [date event link tweet-id] :as s} (filter #(= "2019" (:year %))  schedule)]
             ^{:key date}
             [:> ui/Table.Row {:style {:textAlign "left"}}
              [:> ui/Table.Cell date]
              [:> ui/Table.Cell
               (cond
                 (some? link)
                 [:a {:href link
                      :style {:cursor "pointer"}} event]
                 (some? tweet-id)
                 [:a {:onClick #(reset! modal-content s)
                      :style {:cursor "pointer"}} event]
                 :else
                 event)]])]]]))))

(defn record-panel []
  [:<>
   [:h2 "This is the Record Page."]])

(defn inquiry-panel []
  [:<>
   [:h2 "連絡先"]
   [:p "名古屋大学相撲部に関するお問い合わせは下記のアドレスまでお願いします。"]
   [:> ui/Image {:src "/img/mail.jpg" :style {:maxWidth "500px"}}]
   [:br]
   [:p "（スパム対策のためアドレスは画像で表示させてあります。）"]
   [:br]
   [:p "もしくは各SNSから連絡を下さい。"]
   [:> ui/Segment {:basic true :textAlign "center"}
    [:a {:href "https://twitter.com/nu_sumo" :target "_blank"}
     [:> ui/Icon {:name "twitter" :style {:color "#1EA1F2"} :size "big"}]]
    [:a {:href "https://www.facebook.com/NUSUMOCLUB/" :target "_blank"}
     [:> ui/Icon {:name "facebook" :style {:color "#4267B2"} :size "big"}]]
    [:a {:href "https://www.instagram.com/nu_sumosumo/" :target "_blank"}
     [:> ui/Icon {:name "instagram" :style {:color "#D12798"} :size "big"}]]]])

(defn declation-panel []
  [:<>
   [:h2 "また名大相撲部宣言"]
   [:p "我ら名古屋大学体育会相撲部は、経済と情報の急激なグローバリゼーションの進行する今日、人類社会の福祉の向上、国際間の平和、地球環境の保全をその使命とし、2000年4月、日本国愛知県名古屋市に設立された。"]
   [:p "我ら名古屋大学体育会相撲部は、地球というこの天体の地表で文化と経済のダイナミズム維持に貢献するために、日本列島が育んできた歴史を継承発展させることをその理想とし、相撲という文化の復興を目論見、研鑽を続けている。"]
   [:p "我ら名古屋大学体育会相撲部は、大量生産、大量消費の際限のない増殖から、循環経済への移行期にあり、物質によらない繁栄の一つの形式の発露として、相撲という文化を通じて遊び通すことに全力を傾注する。"]
   [:p "我ら名古屋大学体育会相撲部は、簡便であることや、コストパフォーマンスを尊ぶ風潮を下品であると断罪し、不必要なまでに格好をつけることに拘り続ける。"]
   [:p "我ら名古屋大学体育会相撲部は、「五層櫓のてっぺんに金のシャチホコ雨ざらし、ああこりゃこりゃ。」の精神を胸に秘め、日々偏見や陋習と戦い続ける。"]
   [:p "我ら名古屋大学体育会相撲部は、何をおいても歌舞き、槍が降ろうが、生首が飛ぼうが、歌舞き続けることをここに宣言する、がや。"]])

(defn freshman-panel []
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
   [:strong "『名大生の諸君、悪いことは言わない、名大相撲部に入り給え！！』"]])

(defn ob-panel []
  [:<>
   [:h2 "OBの就職先"]
   [:h3 "あなたには履歴書に書ける「自己PR」がありますか？"]
   "就職氷河期で買い手市場となった今、旧帝大卒だからといって簡単に内定が貰える訳ではありません。" [:br]
   "しかし、不況にも関わらず体育会の学生が大手企業に就職しているのは、紛れも無い事実です。" [:br] [:br]
   "履歴書（ES）や面接では主に「志望動機」「研究内容」「自己PR」を聞かれますが、企業が大手であればあるほど「志望動機」や「研究内容」よりも「自己PR」を重視する傾向があります。" [:br]
   "「自己PRをして下さい」「あなたの強みは何ですか」「学生時代に頑張ったことは何ですか」と聞かれた時に、アルバイトやサークルの話ではなく部活動の話をすることにより、満足のいく就職活動を終えることができるよう、心からお祈り申し上げます。" [:br]
   [:h3 "主な就職先（五十音順）"]
   [:> ui/List {:bulleted true}
    [:> ui/List.Item "愛知県庁"]
    [:> ui/List.Item "アクセンチュア"]
    [:> ui/List.Item "旭化成"]
    [:> ui/List.Item "朝日新聞社"]
    [:> ui/List.Item "味の素"]
    [:> ui/List.Item "伊藤忠商事"]
    [:> ui/List.Item "NTTドコモ"]
    [:> ui/List.Item "大垣共立銀行"]
    [:> ui/List.Item "岡崎信用金庫"]
    [:> ui/List.Item "鹿島建設"]
    [:> ui/List.Item "キリンビール"]
    [:> ui/List.Item "国際協力銀行"]
    [:> ui/List.Item "サントリー"]
    [:> ui/List.Item "新日本製鐵"]
    [:> ui/List.Item "中日新聞社"]
    [:> ui/List.Item "中部電力"]
    [:> ui/List.Item "電源開発"]
    [:> ui/List.Item "電通"]
    [:> ui/List.Item "デンソー"]
    [:> ui/List.Item "東海旅客鉄道"]
    [:> ui/List.Item "トヨタ自動車"]
    [:> ui/List.Item "豊田自動織機"]
    [:> ui/List.Item "豊田通商"]
    [:> ui/List.Item "名古屋市役所"]
    [:> ui/List.Item "名古屋市消防局"]
    [:> ui/List.Item "名古屋鉄道"]
    [:> ui/List.Item "日本ガイシ"]
    [:> ui/List.Item "日本相撲協会"]
    [:> ui/List.Item "日本たばこ産業"]
    [:> ui/List.Item "博報堂"]
    [:> ui/List.Item "三井住友銀行"]
    [:> ui/List.Item "三菱重工業"]
    [:> ui/List.Item "三菱UFJ銀行"]
    [:> ui/List.Item "リクルート"]]
   [:br]
   "などなど。"
   [:br] [:br]
   [:img {:src "/img/obog.jpg" :className "ui image" :alt "OB/OG"}]])

(defn keiko-panel []
  [:<>
   [:h2 "稽古詳細"]
   [:span (str "相撲部における稽古の詳細です。日によって内容は変わりますが、基本的な流れは下記の通り。"
               "四股や摺り足などの基礎トレーニングから始め、実践的な三番を行います。"
               "それぞれの稽古の意味をよく考えながら取り組みます。"
               "稽古時間は大体二時間から三時間です。")]
   [:> ui/Card {:style {:width "100%" :maxWidth "500px"}}
    [:img {:src "/img/keiko.jpg" :className "ui image" :alt "稽古風景"}]]
   [:> ui/List {:ordered true }
    [:> ui/List.Item  "蹲踞礼"]
    [:> ui/List.Item  "準備運動"]
    [:> ui/List.Item  "四股八十八回"]
    [:> ui/List.Item  "摺り足"]
    [:> ui/List.Item  "押し"]
    [:> ui/List.Item  "三番"]
    [:> ui/List.Item  "ぶつかり"]
    [:> ui/List.Item  "蹲踞礼"]
    [:> ui/List.Item  "掃除、風呂、ちゃんこ"]]])

(defn chanko-panel []
  [:<>
   [:h2 "名大相撲部ちゃんこ"]
   [:span "名大相撲部のオリジナルちゃんこを紹介します。週末の稽古で作り、皆で食べています。"]
   [:> ui/Card {:style {:width "100%" :maxWidth "500px"}}
    [image {:image-path "/img/chanko.jpg" :alt "ちゃんこ"}]]
   [:h3 "材料"]
   [:> ui/List {:bulleted true}
    [:> ui/List.Item "昆布"]
    [:> ui/List.Item "鰹だし(顆粒)"]
    [:> ui/List.Item "鶏手羽先"]
    [:> ui/List.Item "豚こま切れ"]
    [:> ui/List.Item "鶏もも肉(むね肉はNG)"]
    [:> ui/List.Item "鶏レバー"]
    [:> ui/List.Item "白菜orキャベツ(安いほう）"]
    [:> ui/List.Item "大根"]
    [:> ui/List.Item "ニンジン"]
    [:> ui/List.Item "ゴボウ"]
    [:> ui/List.Item "しいたけ"]
    [:> ui/List.Item "こんにゃく"]
    [:> ui/List.Item "油揚げ"]
    [:> ui/List.Item "豆腐"]
    [:> ui/List.Item "水菜"]
    [:> ui/List.Item "その他季節に応じて。"]]
   [:h3 "作り方"]
   [:> ui/List {:ordered true}
    [:> ui/List.Item "材料を適当な大きさに切り、鶏レバーの血抜き、こんにゃくと油揚げの湯通しをする。"]
    [:> ui/List.Item "大きな鍋にたっぷりの水と少量の酒を入れ、昆布、鶏手羽先、大根、ニンジン、ゴボウを加えて火を点ける。"]
    [:> ui/List.Item "沸騰したら、鶏もも肉、鶏レバー、しいたけ、こんにゃく、油揚げ、白菜、豚こま切れ、を順に加える。"]
    [:> ui/List.Item "アクを取り、鰹だし、塩、しょうゆで味を調える。"]
    [:> ui/List.Item "火を止め、豆腐、水菜を加えて出来上がり。"]]])

(defn ibukioroshi-panel []
  [:<>
   [:h2 "第八高等学校寮歌 伊吹おろし"]
   [:span (str "名古屋大学体育会に伝わる伝統の寮歌です。"
               "祝い事や行事の際に歌います。")]
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
   [:h3 "歌詞"]
   [:table.ibukioroshi
    [:tbody
     [:tr
      [:td "一、"]
      [:td
       "伊吹おろしの雪消えて   木曽の流れに囁けば\n"
       "光に満てる国原の   春永劫(えいごう)に薫るかな"]]
     [:tr
      [:td "二、"]
      [:td
       "夕日あふれて草萌ゆる   瑞穂丘に佇めば\n"
       "零(こぼ)れ地に咲く花菜にも   うら若き子は涙する"]]
     [:tr
      [:td "三、"]
      [:td
       "見よソロモンの栄耀も   野の白百合に及(し)かざるを\n"
       "路傍の花にゆき暮れて   果てなき夢の姿かな"]]
     [:tr
      [:td "四、"]
      [:td
       "花に滴る日の水沫(みずわ)   命の啓示(さとし)を語るとき\n"
       "希望(のぞみ)に滾(た)ぎる若き頬を   はるかに星は照すなり"]]
     [:tr
      [:td "五、"]
      [:td
       "神秘の闇のおとずれに   いつしか寮の灯火(ともしび)は\n"
       "瞬き(またたき)そめて我を待つ   地上の夢よいざ去らば"]]
     [:tr
      [:td "六、"]
      [:td
       "杳(よう)靄(あい)融(と)けし丘の上に   いづくともなく春をよぶ\n"
       "歌やすらかに流れ来る   紺青(こんじょう)の月影濃けれ"]]]]
   [image {:image-path "/img/ibukioroshi.jpg" :alt "伊吹おろし"}]])

(defn monbetsu-gassyuku-2019-panel []
  [:<>
   [:h2 "紋別合宿2019"]
   [:p "春休みにも北大相撲部と合宿を行います"]
   [:> ui/Grid
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/monbetsu_2019_1.jpg" :className "ui image" :alt "紋別合宿1"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "太平洋フェリー"]
     "この合宿は名古屋からフェリーで向かいます\n甲板での一枚"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "観光①"]
     "アザラシとのふれあい\nアザラシは僕たちの心を癒やしてくれました"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/monbetsu_2019_2.jpg" :className "ui image" :alt "紋別合宿2"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/monbetsu_2019_3.jpg"  :className "ui image" :alt "紋別合宿3"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "観光②"]
     "北海道に来たからにはスキーをせずにはいられません"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "食事風景"]
     "名大と北大が交互に作ります"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/monbetsu_2019_4.jpg" :className "ui image" :alt "紋別合宿4"
            :style {:width "100%"}}]]]
   [:br]
   [:p {:style {:textAlign "center"}}
    [:a {:href "/ozoracho-gassyuku-2019" :style {:fontSize "20px"}}
     "> 大空町合宿2019"]]])

(defn ozoracho-gassyuku-2019-panel []
  [:<>
   [:h2 "大空町合宿2019"]
   [:p "七帝戦の直後、同門の北大相撲部と合同合宿を行います"]
   [:> ui/Grid
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/ozoracho_2019_1.jpg" :className "ui image" :alt "大空町合宿1"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "稽古風景"]
     "普段やらない相手との稽古はとても身になります"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "食事風景"]
     "大人数でたくさんご飯を食べます"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/ozoracho_2019_2.jpg" :className "ui image" :alt "大空町合宿2"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/ozoracho_2019_3.jpg" :className "ui image" :alt "大空町合宿3"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "観光"]
     "北海道の大自然もしっかり満喫"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "名北戦"]
     "名大北大対抗戦も行います\n今年は名大の勝利"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/ozoracho_2019_4.jpg" :className "ui image" :alt "大空町合宿4"
            :style {:width "100%"}}]]]
   [:br]
   [:p {:style {:textAlign "center"}}
    [:a {:href "/toshijima-gassyuku-2019" :style {:fontSize "20px"}}
     "> 答志島合宿2019"]]])

(defn toshijima-gassyuku-2019-panel []
  [:<>
   [:h2 "答志島合宿2019"]
   [:p "毎年夏休みに三重県の答志島にて合宿を行います。"]
   [:> ui/Grid
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/toshijima_2019_1.jpg" :className "ui image" :alt "答志島合宿1"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "稽古風景①"]
     "元々答志小学校にある土俵に加えて\n自分たちで耕して土俵を作り稽古に\n励みます"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "稽古風景②"]
     "OBの方々も来て下さり胸を貸してくれます。"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/toshijima_2019_2.jpg" :className "ui image" :alt "答志島合宿2"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/toshijima_2019_3.jpg" :className "ui image" :alt "答志島合宿3"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "稽古風景③"]
     "稽古後は海で泳いでアイシングします。"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "食事風景"]
     "宿のご飯はずっと食べてしまうくらいとてもおいしいです"]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/toshijima_2019_4.jpg" :className "ui image" :alt "答志島合宿4"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:img {:src "/img/toshijima_2019_5.jpg" :className "ui image" :alt "答志島合宿5"
            :style {:width "100%"}}]]
    [:> ui/Grid.Column {:mobile 8 :computer 8 :style {:whiteSpace "pre-line"}}
     [:h3 "盆踊り大会"]
     "答志島の夏の風物詩\n相撲部も島民と一緒に踊り続けます"]]
   [:br]
   [:p {:style {:textAlign "center"}}
    [:a {:href "/monbetsu-gassyuku-2019" :style {:fontSize "20px"}}
     "> 紋別合宿2019"]]])

(defn media-panel []
  [:<>
   [:h2 "メディア・出演情報"]
   [:br]
   [:> ui/Grid
    [:> ui/Grid.Column {:mobile 16 :computer 8}
     [:> ui/Segment {:style {:whiteSpace "pre-line"}}
      [:h3 "2017年 毎日新聞"]
      [:a {:href "https://mainichi.jp/articles/20170427/ddh/041/100/002000c"}
       "名古屋大 相撲部、苦境うっちゃれ！！！！ 部員減、仲間求む"]]]

    [:> ui/Grid.Column {:mobile 16 :computer 8}
     [:> ui/Segment {:style {:whiteSpace "pre-line"}}
      [:h3 "2017年 中日新聞"]
      [:a {:href "https://www.facebook.com/NUSUMOCLUB/posts/1567295713364302?__tn__=-R"}
       "人情交差点"]
      #_[:iframe {:src "https://www.facebook.com/plugins/post.php?href=https%3A%2F%2Fwww.facebook.com%2FNUSUMOCLUB%2Fposts%2F1567295713364302&width=350"
                  :width "330" :height "600" :style {:border "none" :overflow "scroll"}
                  :scrolling "no" :frameborder "0" :allowTransparency "true" :allow "encrypted-media"}]]]

    [:> ui/Grid.Column {:mobile 16 :computer 8}
     [:> ui/Segment {:style {:whiteSpace "pre-line"}}
      [:h3 "2013年 オードリーさん、ぜひ会ってほしい人がいるんです。"]
      [:p [:a {:href "https://www.youtube.com/watch?v=D-L2jg46wbU"}
           "名古屋大学相撲部 相撲嫌いな後輩"]]
      [image {:image-path "http://img.youtube.com/vi/D-L2jg46wbU/0.jpg" :alt "名古屋大学相撲部 相撲嫌いな後輩"}
       "https://www.youtube.com/watch?v=D-L2jg46wbU"]]]

    [:> ui/Grid.Column {:mobile 16 :computer 8}
     [:> ui/Segment {:style {:whiteSpace "pre-line"}}
      [:h3 "2011年 スポニチ"]
      [:a {:href "https://www.sponichi.co.jp/sports/news/2011/03/02/kiji/K20110302000347350.html?feature=related"}
       "名古屋大出身・舛名大が引退…何と相撲記者に"]]]

    [:> ui/Grid.Column {:mobile 16 :computer 8}
     [:> ui/Segment {:style {:whiteSpace "pre-line"}}
      [:h3 "2009年 名駅経済新聞"]
      [:a {:href "https://meieki.keizai.biz/headline/923/"}
       "アマチュア相撲とクラブ音楽のコラボイベント－今年も名古屋城で開催へ"]]]

    [:> ui/Grid.Column {:mobile 16 :computer 8}
     [:> ui/Segment {:style {:whiteSpace "pre-line"}}
      [:h3 "2008年 どすこい!!名古屋城RAVE2008"]
      [:p [:a {:href "https://www.youtube.com/watch?v=xX8qpvD0GfI"}
           "どすこい!!名古屋城RAVE2008 PV"]]
      [image {:image-path "http://img.youtube.com/vi/xX8qpvD0GfI/0.jpg" :alt "どすこい!!名古屋城RAVE2008 PV"}
       "https://www.youtube.com/watch?v=xX8qpvD0GfI"]]]

    [:> ui/Grid.Column {:mobile 16 :computer 8}
     [:> ui/Segment {:style {:whiteSpace "pre-line"}}
      [:h3 "舛名大周一"]
      [:p "名大相撲部より、旧帝大出身者初の大相撲力士として舛名大が誕生しました。"]
      [:a {:href "https://ja.wikipedia.org/wiki/%E8%88%9B%E5%90%8D%E5%A4%A7%E5%91%A8%E4%B8%80"}
       "Wikipedia: 舛名大"]]]]])

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
(defmethod panels :monbetsu-gassyuku-2019-panel [] [monbetsu-gassyuku-2019-panel])
(defmethod panels :ozoracho-gassyuku-2019-panel [] [ozoracho-gassyuku-2019-panel])
(defmethod panels :toshijima-gassyuku-2019-panel [] [toshijima-gassyuku-2019-panel])
(defmethod panels :media-panel [] [media-panel])
(defmethod panels :default [] [:div])

(defn show-panel [panel-name]
  [panels panel-name])
