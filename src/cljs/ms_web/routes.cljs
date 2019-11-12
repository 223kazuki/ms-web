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
  (defroute "/about" []
    (re-frame/dispatch [::events/set-active-panel :about-panel]))

  (hook-browser-navigation!))
