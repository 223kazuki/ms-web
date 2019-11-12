(ns ms-web.views
  (:require [ms-web.events :as events]
            [ms-web.subs :as subs]
            [re-frame.core :as re-frame]
            ["react-transition-group" :as tg]
            ["semantic-ui-react" :as ui]))

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 (str "Hello from " @name ". This is the Home Page.")]
     [:div
      [:a {:href "#/about"}
       "go to About Page"]]]))

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

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [:> ui/Container {:className "mainContainer" :style {:marginTop "7em"}}
     [:> ui/Menu {:pointing true :secondary true}
      (for [{:keys [key] :as item} [{:name "ホーム" :key "home"}
                                    {:name "名大相撲部" :key "about"}
                                    {:name "部員一覧" :key "member"}
                                    {:name "年間予定" :key "schedule"}
                                    {:name "戦績" :key "record"}
                                    {:name "問い合わせ" :key "inquiry"}]
            :let [panel (keyword (str key "-panel"))]]
        [:> ui/Menu.Item (assoc item :active (= @active-panel panel)
                                :onClick #(re-frame/dispatch [::events/set-active-panel panel]))])]
     [:> tg/TransitionGroup {:className "transition"}
      [:> tg/CSSTransition {:key @active-panel
                            :classNames "transition"
                            :timeout 300}
       [show-panel @active-panel]]]]))
