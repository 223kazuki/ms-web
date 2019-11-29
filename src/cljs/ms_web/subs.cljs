(ns ms-web.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

(re-frame/reg-sub
 ::url-params
 (fn [db _]
   (:url-params db)))

(re-frame/reg-sub
 ::menu-open?
 (fn [db _]
   (:menu-open? db)))

(re-frame/reg-sub
 ::parent
 (fn [db _]
   (:parent db)))

(re-frame/reg-sub
 ::sub-menu
 (fn [db _]
   (:sub-menu db)))

(re-frame/reg-sub
 ::members
 (fn [db _]
   (:members db)))
