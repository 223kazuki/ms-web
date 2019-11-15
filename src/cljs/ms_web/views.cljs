(ns ms-web.views
  (:require [ms-web.events :as events]
            [ms-web.subs :as subs]
            [re-frame.core :as re-frame]
            [reagent.core :as r]
            ["react-transition-group" :as tg]
            ["semantic-ui-react" :as ui]))

(def visibility (r/atom false))

(defn home-panel []
  (let []
    [:div
     [:> ui/Visibility {:onTopPassed #(reset! visibility true)}
      [:> ui/Header {:as "h1"
                     :inverted true
                     :style {:width "100%"
                             :height 200
                             :display "inline-block"
                             :opacity 0.5
                             :backgroundImage "url(\"/img/top.jpg\")"
                             :backgroundSize "cover"
                             :background "center"
                             :fontSize "3em"
                             :fontWeight "normal"
                             :textAlign "center"}}
       "名古屋大学相撲部"]]
     [:> ui/Transition {:visible @visibility :animation "scale" :duration 500}
      [:> ui/Header {:as "h1"
                     :inverted true
                     :style {:width "100%"
                             :height 200
                             :display "inline-block"
                             :opacity 0.5
                             :backgroundImage "url(\"/img/top.jpg\")"
                             :backgroundSize "cover"
                             :background "center"
                             :fontSize "3em"
                             :fontWeight "normal"
                             :textAlign "center"}}
       "名古屋大学相撲部"]]]))

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]
   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])

(defn member-panel []
  [:div
   [:h1 "This is the Member Page."]])

(defn schedule-panel []
  [:div
   [:h1 "This is the Schedule Page."]])

(defn record-panel []
  [:div
   [:h1 "This is the Record Page."]])

(defn inquiry-panel []
  [:div
   [:h1 "This is the Inquiry Page."]])

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :member-panel [] [member-panel])
(defmethod panels :schedule-panel [] [schedule-panel])
(defmethod panels :record-panel [] [record-panel])
(defmethod panels :inquiry-panel [] [inquiry-panel])
(defmethod panels :default [] [:div])

(defn show-panel [panel-name]
  [panels panel-name])

(def menu [{:name "ホーム" :key "home" :icon "home"}
           {:name "名大相撲部" :key "about" :icon "info"}
           {:name "部員名簿" :key "member" :icon "users"}
           {:name "年間予定" :key "schedule" :icon "calendar"}
           {:name "戦績" :key "record" :icon "book"}
           {:name "問い合わせ" :key "inquiry" :icon "mail"}])

(defn pc-container []
  (let [active-panel @(re-frame/subscribe [::subs/active-panel])]
    [:> ui/Container {:className "mainContainer"}
     [:> ui/Menu {:pointing true :secondary true}
      (for [{:keys [key name] :as item} menu
            :let [panel (keyword (str key "-panel"))]]
        ^{:key key}
        [:> ui/Menu.Item {:name name
                          :active (= active-panel panel)
                          :onClick #(re-frame/dispatch [::events/set-active-panel panel])}])]
     [:> tg/TransitionGroup {:className "transition"}
      [:> tg/CSSTransition {:key active-panel :classNames "transition" :timeout 300}
       [show-panel active-panel]]]]))

(defn mobile-container []
  (let [active-panel @(re-frame/subscribe [::subs/active-panel])
        menu-open? @(re-frame/subscribe [::subs/menu-open?])]
    [:> ui/Container {:className "mainContainer"}
     [:> ui/Sidebar.Pushable
      [:> ui/Sidebar {:as ui/Menu
                      :animation "push"
                      :icon "labeled"
                      :vertical true
                      :visible menu-open?
                      :width "thin"}
       (for [{:keys [key name icon] :as item} menu
             :let [panel (keyword (str key "-panel"))]]
         ^{:key key}
         [:> ui/Menu.Item {:as "a"
                           :active (= active-panel panel)
                           :href (str "#/" key)}
          [:> ui/Icon {:name icon}]
          name])]
      [:> ui/Sidebar.Pusher
       [:> ui/Segment {:basic true :style {:min-height "100vh" :padding 0}}
        [:> ui/Menu {:fixed "top" :className "noStyle"}
         [:a {:className (str "menuButton" (when menu-open? " active"))
              :onClick #(re-frame/dispatch [::events/toggl-menu-open])}
          [:span]
          [:span]
          [:span]]]
        [:> tg/TransitionGroup {:className "transition"}
         [:> tg/CSSTransition {:key active-panel :classNames "transition" :timeout 300}
          [show-panel active-panel]]]]]]]))

(defn main-container []
  [:<>
   [:> ui/Responsive {:minWidth 1201}
    [pc-container]]
   [:> ui/Responsive {:maxWidth 1200}
    [mobile-container]]])
