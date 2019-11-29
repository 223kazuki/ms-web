(ns ms-web.events
  (:require [ms-web.db :as db]
            [re-frame.core :as re-frame]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel params]]
   (assoc db
          :active-panel active-panel
          :url-params params
          :menu-open? false
          :parent nil
          :sub-menu nil)))

(re-frame/reg-event-db
 ::toggl-menu-open
 (fn [db [_ _]]
   (update db :menu-open? not)))

(re-frame/reg-event-db
 ::set-sub-menu
 (fn [db [_ parent sub-menu]]
   (assoc db :parent parent :sub-menu sub-menu)))
