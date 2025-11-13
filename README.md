# 🧩 Gerador de QR Code

Aplicação desenvolvida em **Java Spring Boot** que gera QR Codes e os armazena na **AWS S3**.  
O projeto utiliza a biblioteca **Google ZXing** para criação dos QR Codes e o **AWS SDK** para integração com o S3.  

---

## 🚀 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java 21 JDK**
- **Maven**
- **Docker** 
- **Conta AWS** com acesso ao **S3**
- **AWS IAM** gerar credenciais e acesso ao S3

---

## ⚙️ Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:
```yaml
AWS_ACCESS_KEY_ID=seu_access_key
AWS_SECRET_ACCESS_KEY=seu_secret_key
```
---

## 💻 Execução Local

1. Crie o arquivo `.env` conforme descrito acima  
2. Compile o projeto:

   ```bash
   mvn clean package
   ```
4. Realize o build com Docker
   ```bash
   docker build -t (nome do container) .
    ```
5. Rode o container
   ```bash
    docker run --env-file -p 8080:8080 (nome do container)
   ```

## 🔗 Endpoints da API

### `POST /qrcode`

Gera um QR Code a partir de um texto e salva a imagem no S3.

**Parâmetro de entrada:**

```json
{
  "text": "https://exemplo.com"
}
 ```

**Sucesso:**

```json
{
  "text": "[https://exemplo.com](https://seu-bucket.s3.sua-regiao.amazonaws.com/uuid-gerado)"
}
 ```

