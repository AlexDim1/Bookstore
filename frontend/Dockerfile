FROM nginx:alpine

COPY pages /usr/share/nginx/html/pages
COPY styles /usr/share/nginx/html/styles
COPY scripts /usr/share/nginx/html/scripts
COPY resources /usr/share/nginx/html/resources
COPY index.html /usr/share/nginx/html/index.html
COPY favicon.ico /usr/share/nginx/html/favicon.ico

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
