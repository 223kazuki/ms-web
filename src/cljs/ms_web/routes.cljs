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
  (defroute "/member" []
    (re-frame/dispatch [::events/set-active-panel :member-panel]))
  (defroute "/schedule" []
    (re-frame/dispatch [::events/set-active-panel :schedule-panel]))
  (defroute "/record" []
    (re-frame/dispatch [::events/set-active-panel :record-panel]))
  (defroute "/inquiry" []
    (re-frame/dispatch [::events/set-active-panel :inquiry-panel]))
  (defroute "/about/declation" []
    (re-frame/dispatch [::events/set-active-panel :declation-panel]))
  (defroute "/about/freshman" []
    (re-frame/dispatch [::events/set-active-panel :freshman-panel]))
  (defroute "/about/ob" []
    (re-frame/dispatch [::events/set-active-panel :ob-panel]))
  (defroute "/about/keiko" []
    (re-frame/dispatch [::events/set-active-panel :keiko-panel]))
  (defroute "/about/chanko" []
    (re-frame/dispatch [::events/set-active-panel :chanko-panel]))
  (defroute "/about/ibukioroshi" []
    (re-frame/dispatch [::events/set-active-panel :ibukioroshi-panel]))
  (defroute "/member/:filter" [filter]
    (println "###" filter)
    (re-frame/dispatch [::events/set-active-panel :member-panel filter]))

  (hook-browser-navigation!))
