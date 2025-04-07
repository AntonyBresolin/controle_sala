# Estágio de build
FROM node:20-alpine AS build

# Definir diretório de trabalho
WORKDIR /app

# Instalar dependências
COPY package.json package-lock.json* ./
RUN npm ci

# Copiar arquivos de código-fonte
COPY . .

# Construir a aplicação - React Scripts (CRA) gera build na pasta /build
RUN npm run build

# Estágio de produção
FROM nginx:stable-alpine AS production

# Configurar o nginx para usar a porta 3000
RUN sed -i 's/listen       80/listen       3000/g' /etc/nginx/conf.d/default.conf

# Adicionar configuração para SPA (aplicações de página única)
RUN sed -i '/location \/ {/a try_files $uri $uri/ /index.html;' /etc/nginx/conf.d/default.conf

# Copiar arquivos estáticos do estágio de build
# Create React App cria os arquivos em /app/build
COPY --from=build /app/build /usr/share/nginx/html

# Expor porta 3000
EXPOSE 3000

# Iniciar nginx
CMD ["nginx", "-g", "daemon off;"]