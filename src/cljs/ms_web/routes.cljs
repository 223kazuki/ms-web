(ns ms-web.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:require  [ms-web.events :as events]
             [re-frame.core :as re-frame]
             [secretary.core :as secretary]
             [pushy.core :as pushy]))

(def history (pushy/pushy secretary/dispatch!
                          (fn [x] (when (secretary/locate-route x) x))))

(defn app-routes []
  (secretary/set-config! :prefix "/")

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
    (re-frame/dispatch [::events/set-active-panel :member-panel filter]))
  (defroute "/monbetsu-gassyuku-2019" []
    (re-frame/dispatch [::events/set-active-panel :monbetsu-gassyuku-2019-panel]))
  (defroute "/ozoracho-gassyuku-2019" []
    (re-frame/dispatch [::events/set-active-panel :ozoracho-gassyuku-2019-panel]))
  (defroute "/toshijima-gassyuku-2019" []
    (re-frame/dispatch [::events/set-active-panel :toshijima-gassyuku-2019-panel]))
  (defroute "/media" []
    (re-frame/dispatch [::events/set-active-panel :media-panel]))

  (pushy/start! history))
