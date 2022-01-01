(ns reframexxample.views
  (:require
   [re-frame.core :as re-frame]
   [reframexxample.events :as events]
   [reframexxample.subs :as subs]
   ))
   
(defn display-user [{:keys [id title body]}]
 [:div.horizontal {:key id}
 [:h2 id]
 [:p title]
 [:span body]
])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        loading (re-frame/subscribe [::subs/loading])
        users (re-frame/subscribe [::subs/users])]
    [:div
     [:h1
      "Hello from " @name]
      (when @loading "Загрузка...")
      (map display-user @users)
      [:button {:on-click #(re-frame/dispatch [::events/fetch-users])} "Обращение к АПИ"]
      [:button {:on-click #(re-frame/dispatch [::events/update-name "Tim"])} "Обновить имя"]
     ]))
