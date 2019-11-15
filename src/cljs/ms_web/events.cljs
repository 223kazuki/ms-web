(ns ms-web.events
  (:require [ms-web.db :as db]
            [re-frame.core :as re-frame]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc db
          :active-panel active-panel
          :menu-open? false)))

(re-frame/reg-event-db
 ::toggl-menu-open
 (fn [db [_ _]]
   (update db :menu-open? not)))
