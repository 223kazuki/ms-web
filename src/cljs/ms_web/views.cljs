(ns ms-web.views
  (:require [ms-web.events :as events]
            [ms-web.subs :as subs]
            [ms-web.views.panels :refer [show-panel]]
            [re-frame.core :as re-frame]
            [reagent.core :as r]
            ["react-transition-group" :as tg]
            ["semantic-ui-react" :as ui]
            ["react-twitter-embed" :as twitter]))

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

(defn footer []
  [:footer
   "Copyright 2019-2024 Nagoya University Sumo Club. All Rights Reserved."])

(defn sns-icons []
  [:> ui/Segment {:basic true :textAlign "center"}
   [:a {:href "https://twitter.com/nu_sumo" :target "_blank"}
    [:> ui/Icon {:name "twitter" :style {:color "#1EA1F2"} :size "big"}]]
   [:a {:href "https://www.facebook.com/NUSUMOCLUB/" :target "_blank"}
    [:> ui/Icon {:name "facebook" :style {:color "#4267B2"} :size "big"}]]
   [:a {:href "https://www.instagram.com/nu_sumosumo/" :target "_blank"}
    [:> ui/Icon {:name "instagram" :style {:color "#D12798"} :size "big"}]]])

(defn scroll-to-top
  [active-panel transition-fn]
  (r/create-class
   {:component-did-mount
    transition-fn

    :reagent-render
    (fn []
      [:div
       [show-panel active-panel]])}))

(defn contents [transition-fn]
  (let [active-panel @(re-frame/subscribe [::subs/active-panel])]
    [:<>
     (if (= active-panel :home-panel)
       [:<>
        [:> ui/Header {:className "topHeader"}
         [:> ui/Responsive {:minWidth 1200}
          [:h1 "名古屋大学相撲部"]]]
        [sns-icons]]
       [:> ui/Header {:as "h1" :className "contentsHeader"}])
     [:> ui/Container {:className "contentsInner"}
      [:> ui/Grid
       [:> ui/Grid.Column {:mobile 16 :computer 11 :style {:whiteSpace "pre-line"}}
        [:> tg/TransitionGroup {:id "contents" :className "transition"}
         [:> tg/CSSTransition {:key active-panel :classNames "transition" :timeout 300}
          [scroll-to-top active-panel transition-fn]]]]
       [:> ui/Grid.Column {:mobile 16 :computer 5}
        [:> ui/Responsive {:minWidth 1201}
         [:> ui/Segment {:basic true}
          [:h2 "新着情報（Twitter）"]
          [:> ui/Segment {:basic true}
           [:> twitter/TwitterTimelineEmbed {:sourceType "profile" :userId 1922508294
                                             :options {:height 550}}]]]]]]]
     [footer]]))

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
              [:> ui/Dropdown.Item {:text (:name item) :as "a"
                                    :href (str "/" key "/" (:key item))}])]]
          ^{:key key}
          [:> ui/Menu.Item {:as "a" :href (str "/" key)
                            :active (= active-panel panel)}
           [:> ui/Icon {:name icon}] name]))]
     [contents #(js/window.scrollTo 0 0)]]))

(defn mobile-container []
  (let [active-panel @(re-frame/subscribe [::subs/active-panel])
        sub-menu     @(re-frame/subscribe [::subs/sub-menu])
        menu-open?   @(re-frame/subscribe [::subs/menu-open?])
        parent       @(re-frame/subscribe [::subs/parent])]
    [:> ui/Container {:className "mainContainer" :style {:height "100%"}}
     [:> ui/Sidebar.Pushable {:style {:height "100%"}}
      [:> ui/Sidebar {:as        ui/Menu
                      :className "mainMenu"
                      :animation "push"
                      :icon      "labeled"
                      :vertical  true
                      :visible   menu-open?
                      :width     "thin"
                      :style     {:height "100%"}}
       (for [{:keys [key name icon sub-menu] :as item} menu
             :let                                      [panel (keyword (str key "-panel"))]]
         ^{:key key}
         [:> ui/Menu.Item (if sub-menu
                            {:active  (= active-panel panel)
                             :onClick #(re-frame/dispatch [::events/set-sub-menu key sub-menu])}
                            {:as     "a"
                             :active (= active-panel panel)
                             :href   (str "/" key)})
          [:> ui/Icon {:name icon}]
          name])]
      [:> ui/Sidebar.Pusher {:style {:height "100%"}}
       [:> ui/Segment {:basic true :style {:min-height "100vh" :padding 0 :height "100%"}}
        [:> ui/Sidebar.Pushable {:style {:min-height "100vh" :padding 0 :height "100%"}}
         [:> ui/Sidebar {:as        ui/Menu
                         :className "subMenu"
                         :animation "push"
                         :icon      "labeled"
                         :vertical  true
                         :visible   (some? sub-menu)
                         :width     "thin"
                         :style     {:height "100%"}}
          (for [{:keys [key name icon] :as item} sub-menu
                :let                             [panel (keyword (str key "-panel"))]]
            ^{:key key}
            [:> ui/Menu.Item {:as     "a"
                              :active (= active-panel panel)
                              :href   (str "/" parent "/" key)}
             name])]
         [:> ui/Sidebar.Pusher {:style {:height "100%"}}
          [:> ui/Segment {:basic true :style {:min-height "100vh" :padding 0 :height "100%"}}
           [:> ui/Menu {:fixed "top" :className "noStyle mobileMenu"}
            [:div.menuButtonWrapper
             [:a {:className (str "menuButton" (when menu-open? " active"))
                  :onClick   #(do
                                (re-frame/dispatch [::events/toggl-menu-open])
                                (when sub-menu
                                  (re-frame/dispatch [::events/set-sub-menu nil])))}
              [:span]
              [:span]
              [:span]]]
            [:div
             [:h1 [:a {:href "/"} "名古屋大学相撲部"]]]]
           [:div#mobile-contents {:style {:height "100vh" :overflow-y "scroll"}}
            [contents #(let [el (js/document.getElementById "mobile-contents")]
                         (set! (.-scrollTop el) 0))]]]]]]]]]))

(defn main-container []
  [:<>
   [:> ui/Responsive {:minWidth 1201}
    [pc-container]]
   [:> ui/Responsive {:maxWidth 1200}
    [mobile-container]]])
