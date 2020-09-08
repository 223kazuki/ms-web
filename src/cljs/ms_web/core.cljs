(ns ms-web.core
  (:require [ms-web.config :as config]
            [ms-web.events :as events]
            [ms-web.routes :as routes]
            [ms-web.views :as views]
            [reagent.core :as reagent]
            [reagent.dom :as reagent.dom]
            [re-frame.core :as re-frame]
            ["react-pdf" :as pdf]))

(set! (.. pdf/pdfjs
          -GlobalWorkerOptions
          -workerSrc)
      "///cdnjs.cloudflare.com/ajax/libs/pdf.js/2.1.266/pdf.worker.js")

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent.dom/render [views/main-container]
                  (.getElementById js/document "app")))

(defn init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
