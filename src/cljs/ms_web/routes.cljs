(ns ms-web.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:require  [goog.events :as gevents]
             [ms-web.events :as events]
             [re-frame.core :as re-frame]
             [secretary.core :as secretary])
  (:import [goog History]
           [goog.history EventType]))

(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
    (re-frame/dispatch [::events/set-active-panel :home-panel]))
  (defroute "/home" []
    (re-frame/dispatch [::events/set-active-panel :home-panel]))
  (defroute "/about" []
    (re-frame/dispatch [::events/set-active-panel :about-panel]))
  (defroute "/member" []
    (re-frame/dispatch [::events/set-active-panel :member-panel]))
  (defroute "/schedule" []
    (re-frame/dispatch [::events/set-active-panel :schedule-panel]))
  (defroute "/record" []
    (re-frame/dispatch [::events/set-active-panel :record-panel]))
  (defroute "/inquiry" []
    (re-frame/dispatch [::events/set-active-panel :inquiry-panel]))

  (hook-browser-navigation!))
