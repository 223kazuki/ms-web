(ns ms-web.db)

(def default-db {:menu-open? false
                 :sub-menu nil
                 :members [{:member/id 30
                            :member/name "飯島琢臣"
                            :member/image "ijima.jpg"
                            :member/introduction1 "主将\n工学部環境土木建築学科\n出身：埼玉県蓮田市"
                            :member/introduction2 "何に対しても積極的に取り組む。"
                            :member/grade "4年"
                            :member/grade-id "grade4"}
                           {:member/id 31
                            :member/name "細川大我"
                            :member/image "hosokawa.jpg"
                            :member/introduction1 "経済学部経営学科\n出身：東京都大田区"
                            :member/introduction2 "イベントの手伝いを積極的に手伝ってくれる。。"
                            :member/grade "4年"
                            :member/grade-id "grade4"}
                           {:member/id 32
                            :member/name "打江泰介"
                            :member/image "uchie.jpg"
                            :member/introduction1 "副将\n経済学部経営学科\n出身：岐阜県高山市"
                            :member/introduction2 "国公立チャンピオン。"
                            :member/grade "3年"
                            :member/grade-id "grade3"}
                           {:member/id 33
                            :member/name "清水貴大"
                            :member/image "shimizu.jpg"
                            :member/introduction1 "情報学部自然情報学科\n出身：愛知県名古屋市"
                            :member/introduction2 "ポケモンが好き。"
                            :member/grade "3年"
                            :member/grade-id "grade3"}
                           {:member/id 34
                            :member/name "杉浦晴海"
                            :member/image "sugiura.jpg"
                            :member/introduction1 "情報学部自然情報学科\n出身：愛知県名古屋市"
                            :member/introduction2 "バイト命。"
                            :member/grade "3年"
                            :member/grade-id "grade3"}
                           {:member/id 35
                            :member/name "岡竜大"
                            :member/image "oka.jpg"
                            :member/introduction1 "主務\n経済学部経営学科\n出身：三重県いなべ市"
                            :member/introduction2 "場を盛り上げてくれるムードメーカー。"
                            :member/grade "3年"
                            :member/grade-id "grade3"}
                           {:member/id 36
                            :member/name "加藤春奈"
                            :member/image "kato.jpg"
                            :member/introduction1 "マネージャー\n工学部機械航空宇宙工学科\n出身：神奈川県横浜市"
                            :member/introduction2 "しっかり者で、同期のまとめ役。"
                            :member/grade "2年"
                            :member/grade-id "grade2"}
                           {:member/id 37
                            :member/name "花井駿佑"
                            :member/image "hanai.jpg"
                            :member/introduction1 "工学部物理工学科\n出身：愛知県名古屋市"
                            :member/introduction2 "サークルと部活を両立している。"
                            :member/grade "2年"
                            :member/grade-id "grade2"}
                           {:member/id 38
                            :member/name "中川悠太"
                            :member/image "nakagawa.jpg"
                            :member/introduction1 "農学部生物環境科学科\n出身：東京都世田谷区"
                            :member/introduction2 "麻雀好きのギャンブラー。"
                            :member/grade "2年"
                            :member/grade-id "grade2"}
                           {:member/id 39
                            :member/name "高橋純輝"
                            :member/image "takahashi.jpg"
                            :member/introduction1 "理学部物理学科\n出身：岐阜県岐阜市"
                            :member/introduction2 "真面目に見えて実はポンコツ。"
                            :member/grade "2年"
                            :member/grade-id "grade2"}
                           {:member/id 40
                            :member/name "尾田駿太"
                            :member/image "oda.jpg"
                            :member/introduction1 "工学部物理工学科\n出身：愛知県名古屋市"
                            :member/introduction2 "手足が長く、廻しを取るのが上手い。"
                            :member/grade "2年"
                            :member/grade-id "grade2"}
                           {:member/id 41
                            :member/name "朴相雄"
                            :member/image "paku.jpg"
                            :member/introduction1 "文学部\n出身：韓国ソウル"
                            :member/introduction2 "韓国人留学生で、マイペース。"
                            :member/grade "1年"
                            :member/grade-id "grade1"}
                           {:member/id 42
                            :member/name "青山遼太郎"
                            :member/image "aoyama.jpg"
                            :member/introduction1 "工学部機械航空宇宙工学科\n出身：愛知県一宮市"
                            :member/introduction2 "先輩のことが好き。"
                            :member/grade "1年"
                            :member/grade-id "grade1"}
                           {:member/id 43
                            :member/name "土屋英貴"
                            :member/image "tsuchiya.jpg"
                            :member/introduction1 "工学部機械航空宇宙工学科\n出身：静岡県浜松市"
                            :member/introduction2 "料理が好きで、いろいろな料理を作れる。"
                            :member/grade "1年"
                            :member/grade-id "grade1"}
                           {:member/id 1
                            :member/name "加藤 延夫"
                            :member/introduction1 "総長"
                            :member/introduction2 "第１０代名古屋大学総長。現在は愛知医科大学理事長。細菌学の大家。相撲部には師範の細谷教授に引きずりこまれる。\n以来、名古屋大学相撲部総長として、いくつもの大会を主催し、合宿の慰問、稽古総見、「どすこい名古屋城ＲＡＶＥ」会長、七大学相撲連盟（七帝大相撲連盟）会長と、四方八方に伸び続ける名大相撲部の活動を庇護して動じない。\n世界最大規模（出場者１００名以上）の規模を誇る学内相撲大会である「加藤杯・名古屋大学学内相撲大会」の加藤杯は、このお方の名を冠としていることは言うまでもない。"
                            :member/grade "首脳陣"
                            :member/grade-id "managers"}
                           {:member/id 2
                            :member/name "細谷 辰之"
                            :member/introduction1 "師範"
                            :member/introduction2 "名大相撲部初代主将。現在も自ら胸を出し、毎年夏の答志島合宿はフル参加。\nパジェロを乗り回し、サングラスをかけ、葉巻をふかし、初対面では教授だとは絶対にわからない。\n「メチャクチャうまい。」という口癖は、某部員に本人のいる前で堂々とものまねされている…。\n相撲初心者相手に内無双や捻り技を繰り出す点は、少々大人げないと思いますよ、細谷先生！"
                            :member/grade "首脳陣"
                            :member/grade-id "managers"}
                           {:member/id 3
                            :member/name "高見秀樹"
                            :member/introduction1 "部長\n名古屋大学医学部附属病院助教"
                            :member/grade "首脳陣"
                            :member/grade-id "managers"}
                           #_{:member/id 4
                              :member/name "小川 光"
                              :member/introduction1 "部長"
                              :member/grade "首脳陣"
                              :member/grade-id "managers"}
                           #_{:member/id 5
                              :member/name "辻 年喜"
                              :member/image "tsuji.jpg"
                              :member/introduction1 "監督"
                              :member/introduction2 "言わずと知れた名大相撲部の名監督。\n現役時代は主将として黄金期を作り上げた。\nトヨタシステムを相撲部に取り入れさらなるパワーアップを目論んでいる。\n紹介者：野原"
                              :member/grade "首脳陣"
                              :member/grade-id "managers"}
                           #_{:member/id 6
                              :member/name "岸根 翔"
                              :member/image "kishine.jpg"
                              :member/introduction1 "助監督"
                              :member/introduction2 "第６代名大相撲部主将で、現在は助監督。\n主将時は、先輩曰く「とても怖かった。」らしいが、その怖さを知る現役部員はいない。\n今でも稽古に来て胸を出し、僕自身も多くの技術指導していただいた。\nただ、最近、彼女さんと相撲部の稽古に来ていただいたのだが、彼女さんの目の前で現役部員に惨敗していた。\nそのときばかりは、本気で八百長しろと思った。\n紹介者：近藤"
                              :member/grade "首脳陣"
                              :member/grade-id "managers"}
                           {:member/id 7
                            :member/name "新美 将平"
                            :member/image "niimi.jpg"
                            :member/introduction1 "監督"
                            :member/introduction2 "一般人とさほど変わらない体格に、相撲部歴代でも憧れＮo.1の筋肉を備えるコーチ。\n現役時代には最終学年での怪我による不本意な引退もあったが、自分にも他人にも厳しい名主将として知られ、今はそのストイックさを生かして街を守る仕事をしている。\n写真の通り、某プロ野球選手に酷似しているが、バックボーンはサッカーである。\n紹介者：堤"
                            :member/grade "首脳陣"
                            :member/grade-id "managers"}
                           {:member/id 8
                            :member/name "近藤 弘章"
                            :member/image "kondou.jpg"
                            :member/introduction1 "コーチ"
                            :member/grade "首脳陣"
                            :member/grade-id "managers"}
                           {:member/id 9
                            :member/name "岩田 将誉"
                            :member/image "iwata.jpg"
                            :member/introduction1 "コーチ"
                            :member/grade "首脳陣"
                            :member/grade-id "managers"}
                           {:member/id 10
                            :member/name "長合 誠也"
                            :member/image "nagou.jpg"
                            :member/introduction1 "コーチ"
                            :member/grade "首脳陣"
                            :member/grade-id "managers"}
                           {:member/id 11
                            :member/name "西田 拓矢"
                            :member/image "nishida.jpg"
                            :member/introduction1 "副将\n工学研究科　電子情報システム\n172cm\n83kg\n可児高校"
                            :member/introduction2 "剣道部を引退した後、相撲部にやってきた。\n相撲部に入った途端に筋トレに目覚める。\nそして無類の筋トレ好きになる。人生で後悔していることは剣道部時代に筋トレしてこなかったことらしい。\nそんな彼も今や相撲部のムードメーカー。\nちなみに彼には立派な生きがいがあるようだが…それを知るのは入部してからになるだろう。\n紹介者　舘"
                            :member/grade "卒業生（2017年度卒）"
                            :member/grade-id "obg2017"}
                           {:member/id 14
                            :member/name "木村 光史郎"
                            :member/image "kimura.jpg"
                            :member/introduction1 "工学部　機械・航空工学科\n169cm\n69kg\n高津高校"
                            :member/introduction2 "かの有名な木村政彦と同じ名字を持つ男。通称、鬼の木村。\n笑いの国大阪からやってきたこともあり、笑いに関してはかなり厳しい。\n小さなことには動じない図太さを兼ね備えており、これが彼の魅力の一つであろう。\nこうしたことから末恐ろしい男であることは間違いない。"
                            :member/grade "卒業生（2017年度卒）"
                            :member/grade-id "obg2017"}
                           {:member/id 15
                            :member/name "上島 裕実"
                            :member/image "kamishima.jpg"
                            :member/introduction1 "中部大学　現代教育学部\n日進西高校"
                            :member/introduction2 "ゆみちむ。中部大学からやって来た、今流行りの相撲女子。\nハイトーンボイスで繰り出される大相撲トークはその場の者を圧倒する。しかし他の部員は相撲ファンではないため、やや空回りしがち。\n絶賛彼氏募集中とのこと。\nマネージャーがんばって！\n紹介者　木村"
                            :member/grade "卒業生（2017年度卒）"
                            :member/grade-id "obg2017"}
                           {:member/id 12
                            :member/name "棚橋 義和"
                            :member/image "tanahashi.jpg"
                            :member/introduction1 "主将\n農学部\n175cm\n85kg\n鳴海高校"
                            :member/introduction2 "いつも部員の想像のはるか上を行く。だけど、ほんとは部活のことを一生懸命考えてる頑張り屋さん。\n空回りしちゃう時もあるよね、わかるよ！\n皆ツンデレだから伝わってないかもしれないけど、そんな棚橋が大好きです∩^ω^∩\n紹介者　魚形"
                            :member/grade "卒業生（2016年度卒）"
                            :member/grade-id "obg2016"}
                           {:member/id 13
                            :member/name "右田 雄基"
                            :member/image "migita.jpg"
                            :member/introduction1 "主務\n法学部\n東筑高校"
                            :member/introduction2 "名前\nミギタユウキ\nタイプ\nどく・かくとう\nとくせい\n笑ってごまかす\n何か起こると　とりあえず笑う\nわざ\nはきだす　　食事中に口から何かをよく飛ばす\nさきおくり　物事に対して優柔不断\nオウムがえし　会話のリアクションがワンパターン\nぜったいれいど　ギャグを言うと必ず失敗する\nおくびょうな　性格\n九州地方からはるばるやってきたようだ\nなんでもそつなくこなす相撲部きっての働き者、上記の特徴から罵倒の対象としての座に君臨している。本人はまんざ らでもないようだ。紹介者とコンビを組ませると最強のムードブレイカーとして機能する。\n紹介者　棚橋"
                            :member/grade "卒業生（2016年度卒）"
                            :member/grade-id "obg2016"}
                           {:member/id 16
                            :member/name "舘 陽太"
                            :member/image "tachi.jpg"
                            :member/introduction1 "経済学部\n170cm\n76kg\n南山高校"
                            :member/introduction2 "顔、体型、雰囲気は少年そのものだが、時々猟奇的な発言が飛び出る。\n三重県から筋トレしながら通学している。先生によると最近立ち位置が分かってきたらしい。\n話し出したら止まらない。\n独り言はもっと止まらない。\n趣味は作詞とテレビとの会話。\n紹介者　西田"
                            :member/grade "卒業生（2015年度卒）"
                            :member/grade-id "obg2015"}
                           {:member/id 17
                            :member/name "壹岐 英明"
                            :member/introduction1 "文学部\n175cm\n75kg"
                            :member/introduction2 "相撲部で唯一の大相撲好きで、よさこいサークルに所属し、アイドルの振りコピを日課とする、悲惨な趣味の方。\nイキポリスと呼ばれ、警察であるかのように振る舞いがちなのでこれまたやばい。\n得意技はイキドリル。\n紹介者　橋本"
                            :member/grade "卒業生（2014年度卒）"
                            :member/grade-id "obg2014"}
                           {:member/id 18
                            :member/name "野澤 大輔"
                            :member/introduction1 "180cm\n83kg\n五条高校"
                            :member/introduction2 "顔だけどんどん太っていく強面のわんちゃん。\nたびたび洒落にならない不幸が彼に振りかかってくる。\n柔道経験者で相撲も結構強いが、なぜかいつも全身傷だらけ。\n怖い。\n紹介者　壱岐"
                            :member/grade "卒業生（2014年度卒）"
                            :member/grade-id "obg2014"}
                           {:member/id 19
                            :member/name "長合 誠也"
                            :member/image "nagou.jpg"
                            :member/introduction1 "経済学部\n174cm\n76kg\n高田高校"
                            :member/introduction2 "①好きな歌手　斉藤和義、東京事変、藍坊主、the pillows、ＡＫＢ４８、DEPAPEPE\n②好きなマンガ　べしゃり暮らし\n③嫌いなスポーツ　ボーリング"
                            :member/introduction3 "自分と同じ高田高校出身の相撲部期待の新人。\n相撲部に入った理由は私こと金城先輩に対する愛とノリの良さ。\n彼の喋りの独特のイントネーションはいつも僕達を爆笑の渦に巻き込む。ってかてめーが使ってるのは三重弁じゃねー！！！\nちなみに彼は最近ＡＫＢ４８にハマっているようです。マジきめぇ。\n紹介者　金城"
                            :member/grade "卒業生（2013年度卒）"
                            :member/grade-id "obg2013"}
                           {:member/id 20
                            :member/name "岩田 将誉"
                            :member/image "iwata.jpg"
                            :member/introduction1 "工学部電気電子情報工学科\n174cm\n85kg\n清風高校"
                            :member/introduction2 "①好きな歌手　マイケルジャクソン\n②好きな球技　パチンコ\n③休日の過ごし方　ひたすら寝る"
                            :member/introduction3 "関西人。かつ相撲には到底似合わないようなイケメン。\nしかしその顔からは想像できないような体格、睡眠欲、取得単位数の持ち主。\nそして無類の女好き。\nまた、マイケル・ジャクソンの曲が流れると思わず踊りだしてしまうほどのジャクソンファン！\n授業で彼を見かけたジャクソンファンの女性は快く彼に勉強を教えてあげてください！\n紹介者　長合"
                            :member/grade "卒業生（2012年度卒）"
                            :member/grade-id "obg2012"}
                           {:member/id 21
                            :member/name "金城 卓史"
                            :member/image "kanashiro.jpg"
                            :member/introduction1 "電気電子情報工学科\n179cm\n82kg\n高田高校"
                            :member/introduction2 "①好きな女優 杉本彩\n②好きな女性のタイプ 熟女\n③好きなＡＶ 熟女モノ"
                            :member/introduction3 "彼は入部当初は真面目で愛らしい黒人(黒い人)だったけど、\n彼の特徴は人の不幸に汚い声で爆笑して、髭が時間ごとに伸びていくタフな、さながら身を守るためにギャングに入るゲットーの黒人(黒い人)である。\nそして相撲が派手。そのエンターテイメント性は、ウィルスミスか、クリスタッカーか。\nしかし、高校時代のあだ名はアボリジニ。\n紹介者　河合"
                            :member/grade "卒業生（2012年度卒）"
                            :member/grade-id "obg2012"}
                           #_{:member/id 22
                              :member/name "福田 彩香"
                              :member/image "fukuda.jpg"
                              :member/introduction1 ""
                              :member/introduction2 ""
                              :member/grade "卒業生（2012年度卒）"
                              :member/grade-id "obg2012"}
                           {:member/id 23
                            :member/name "大場 泉帆"
                            :member/image "ooba.jpg"
                            :member/introduction1 "椙山大学教育学部\n椙山高校"
                            :member/introduction2 "①最近のマイブーム　餃子\n②最近のお気に入りのグループ　THE　BLUE　HEARTS\n③最近の好きなブログ　越中詩郎さんのブログ"
                            :member/introduction3 "ダイナマイトエンジェル。\nご飯を固く炊いておじやにするのが得意。最近思春期らしく、目の前で着替えると怒る。でもギャグとか言うと許してくれる。たまに壊れたように笑う。\nハートフルエンジェル。\n相撲が好きではない部員の相撲を頑張る原動力……なのかな？\n紹介者　岩田"
                            :member/grade "卒業生（2012年度卒）"
                            :member/grade-id "obg2012"}
                           {:member/id 24
                            :member/name "河合 賢吾"
                            :member/image "kawai.jpg"
                            :member/introduction1 "工学部物理工学科量子エネルギーコース\n180cm\n95kg\n成章高校"
                            :member/introduction2 "①好きな有名人　みひろ、東野幸治、Kiroro、ハロプロ\n②好きなアーティスト　RHYMESTER、スチャダラパー、メローイエロー、韻踏合組合、BUDDHABRAND、餓鬼レンジャー、GAGLE、サイプレス上野とロベルト吉野、steruss、随喜と真田、NITROMICROPHONEUNDERGROUND、降神、SMRYTRPS、Da.Me.Records、PublicEnemy、RUN DMC、faith、尾崎紀世彦、尾崎豊、チャットモンチー、Kiroro、ハロプロ\n③好きなブランド　LRG"
                            :member/introduction3 "ボウズだし無駄に体が大きいし顔がテカテカしてるし独り言とかよく言うし、出来れば近づきたくないタイプの人間。\nだけど本当は後輩のナゴーを連れてモーニング娘。のライブに行ったりする寂しがりやのデブ。\n廻しを巻けば（見た目の通り）国公立大学最強説も噂される次期主将。\nマイクを握れば（見た目の通り）ILLでDOPEな黄色いB-BOY。\nそんな彼は体育会常任委員の激務と学業の両立にも励んでいる一流の名大生でもある。\n（紹介者）堤"
                            :member/grade "卒業生（2011年度卒）"
                            :member/grade-id "obg2011"}
                           {:member/id 25
                            :member/name "門松 春女"
                            :member/image "kadomatsu.jpg"
                            :member/introduction1 "法学部\n鳥取県立砂丘高校"
                            :member/introduction2 "プリンセス名古屋2010という胡散臭いミスコンのセミファイナリストでありながら、モンハンで主人公の名前を「ベジータ」にするような隠れオタク。\n門松が飲み会に来るとだいたい何かしらの問題が起こります。"
                            :member/grade "卒業生（2011年度卒）"
                            :member/grade-id "obg2011"}
                           {:member/id 26
                            :member/name "近藤 弘章"
                            :member/image "kondou.jpg"
                            :member/introduction1 "理学部数理学科\n172cm\n88kg\n一宮高校"
                            :member/introduction2 "①好きな有名人　名大ファミマの芝本さん。\n②好きな曲　JUDY AND MARYの「Over Drive」。\n③実は最近はまっていること　青汁を１日１杯飲むこと。"
                            :member/introduction3 "相撲部の中でも数少ないまともな人間、近藤さん!\nいつも、同期の野原さんと相撲部の異常さと自分達のまともさについて話している様な気がする…。\nそれに、誰よりも自分に厳しく食べている!!最高にネガティブで最高にカッコいい我らが主将です(^o^)／\n（紹介者）大場"
                            :member/grade "卒業生（2010年度卒）"
                            :member/grade-id "obg2010"}
                           {:member/id 27
                            :member/name "堤 一樹"
                            :member/image "tsutsumi.jpg"
                            :member/introduction1 "理学部物理学科\n167cm\n69kg\n立川高校"
                            :member/introduction2 "①趣味　くちごたえ\n②好きな音楽　GRAPEVINE、the pillows、eastern youth、Hermann H. & the Pacemakers、つばき、apnea、ART-SCHOOL、GOING UNDER GROUND、weezer、Jimmy Eat World、Radiohead、Sigur Rós、KEANE、Waking Ashland\n③好きな飲み物　ナンプラー"
                            :member/introduction3 "インドからガンジーの呪いを持ち帰ってきた。おいはぎにあったり原チャリ盗まれる等様々な不幸に見舞われたが最近呪いを解き放ち見事大学院に合格した。\n口癖は「俺の原チャリ盗んだやつマジ撲殺したる！」\n彼が犯罪者にならないことを願うばかりである。\n（紹介者）野原"
                            :member/grade "卒業生（2010年度卒）"
                            :member/grade-id "obg2010"}
                           {:member/id 28
                            :member/name "野原 裕史"
                            :member/image "nohara.jpg"
                            :member/introduction1 "経済学部経営学科\n165cm\n63kg\n天白高校"
                            :member/introduction2 "①好きな映画　狂気の桜\n②好きな言葉　かちこみ\n③趣味　昼ドラ"
                            :member/introduction3 "もし、この世の中が、「嘘ついたーら、針１０００本飲ーます、指切った。」ってルールだったら、すでに１兆本ぐらい針を飲ませられてるはず…。\nメールを送っても、返信してくる確率は、イチローがヒットを打つ確率ぐらい…。\n相撲とるのは大嫌いと平然と口にする…。\nそんな男！でも、憎めないんだな。\n（紹介者）近藤"
                            :member/grade "卒業生（2010年度卒）"
                            :member/grade-id "obg2010"}
                           {:member/id 29
                            :member/name "伊藤 かほり"
                            :member/image "itou.jpg"
                            :member/introduction1 "愛知淑徳大学文学部英文学科\n名古屋大学教育学部附属高校"
                            :member/introduction2 "最近はまっているもの　オーガニック製品\n最近したいこと①　玉ねぎとかの草木染め\n最近したいこと②　沖縄の三線"
                            :member/grade "卒業生（2010年度卒）"
                            :member/grade-id "obg2010"}]
                 :schedule [{:year "2019" :date "1/6" :event "初稽古"}
                            {:year "2019" :date "1/22 - 2/5" :event "オフ"}
                            {:year "2019" :date "3/3 - 12" :event "紋別合宿"
                             :link "/monbetsu-gassyuku-2019" :tweet-id "1103286860267180034"}
                            {:year "2019" :date "4/17" :event "加藤杯" :tweet-id "1118489509467832320"}
                            {:year "2019" :date "5/5" :event "東海近畿オープン" :tweet-id "1125039258899632128"}
                            {:year "2019" :date "5/19" :event "国公立大会" :tweet-id "1130117437007859712"}
                            {:year "2019" :date "6/2" :event "西日本インカレ" :tweet-id "1135157431133065216"}
                            {:year "2019" :date "6/6" :event "留学生交流会" :tweet-id "1136844554491973633"}
                            {:year "2019" :date "6/22" :event "高砂部屋激励会" :tweet-id "1142448775572574209"}
                            {:year "2019" :date "7/21" :event "西日本体重別"}
                            {:year "2019" :date "8/9 - 16" :event "答志島合宿"
                             :link "/toshijima-gassyuku-2019" :tweet-id "1160407210465189888"}
                            {:year "2019" :date "9/1" :event "全国体重別" :tweet-id "1167960365478469632"}
                            {:year "2019" :date "9/8" :event "七帝戦" :tweet-id "1170714652478590977"}
                            {:year "2019" :date "9/9 - 15" :event "大空町合宿"
                             :link "/ozoracho-gassyuku-2019" :tweet-id "1171804679224713216"}
                            {:year "2019" :date "9/16 - 9/30" :event "オフ"}
                            {:year "2019" :date "10/5" :event "大須素人相撲大会" :tweet-id "1179743052534956034"}
                            {:year "2019" :date "10/19" :event "名防戦" :tweet-id "1185917453190103047"}
                            {:year "2019" :date "11/2, 3" :event "全国学生相撲選手権大会(インカレ)" :tweet-id "1191168671571267584"}
                            {:year "2019" :date "11/20" :event "細谷先生還暦パーティー＠名古屋"}
                            {:year "2019" :date "11/22" :event "細谷先生還暦パーティー＠東京"}
                            {:year "2019" :date "11/23, 24" :event "細谷先生還暦旅行" :tweet-id "1198811313956220929"}
                            {:year "2019" :date "12/28" :event "納稽古" :tweet-id "1210914057848422401"}
                            {:year "2019" :date "12/29, 30" :event "納旅行" :tweet-id "1211562268535607296"}

                            {:year "2020" :date "1/5" :event "初稽古"}
                            {:year "2020" :date "1/18~2/10" :event "テスト休み"}
                            {:year "2020" :date "2/18" :event "推薦の細道" :tweet-id "1229568381319184384"}
                            {:year "2020" :date "2/19~25" :event "津別合宿" :tweet-id "1232276702430023686"}
                            {:year "2020" :date "2/25~3/10" :event "部活OFF"}
                            {:year "2020" :date "3/12, 13" :event "地獄の細道"}
                            {:year "2020" :date "3/16~26" :event "毎日稽古"}
                            {:year "2020" :date "3/20~22" :event "土俵改修"}
                            {:year "2020" :date "4/2, 3" :event "花見"}
                            {:year "2020" :date "4/4" :event "ちゃんこパーティー"}
                            {:year "2020" :date "4/5" :event "入学祭典、ESTでのカンパ"}
                            {:year "2020" :date "4/6" :event "バケツ企画"}
                            {:year "2020" :date "4/9" :event "ちゃんこ炊き出し"}
                            {:year "2020" :date "4/14" :event "武道系新歓"}
                            {:year "2020" :date "4/15" :event "素人相撲大会"}
                            {:year "2020" :date "4/20" :event "パンケーキパーティー"}
                            {:year "2020" :date "4/24" :event "ぎょうざパーティー"}
                            {:year "2020" :date "5/3" :event "東海近畿"}
                            {:year "2020" :date "5/4" :event "BBQ"}]
                 :keiko ["毎週火曜5時～"
                         "毎週木曜5時～"
                         "毎週土曜9時半～"]})
