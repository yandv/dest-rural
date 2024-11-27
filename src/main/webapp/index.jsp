<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Formulário de Estágio</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        background-color: #f5f5f5;
      }
      .container {
        background: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 500px;
      }
      .container h1 {
        text-align: center;
        margin-bottom: 20px;
      }
      .form-section {
        margin-bottom: 20px;
      }
      .form-section h2 {
        font-size: 1.2em;
        margin-bottom: 10px;
      }
      .form-group {
        margin-bottom: 15px;
      }
      .form-group label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
      }
      .form-group input,
      .form-group textarea,
      .form-group select {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
      }
      .form-group textarea {
        resize: vertical;
      }
      .form-group input[type="checkbox"] {
        width: auto;
      }
      .submit-btn {
        display: block;
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 1em;
        cursor: pointer;
      }
      .submit-btn:hover {
        background-color: #0056b3;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>Formulário de Criação de Pedido de Intenção de Estágio</h1>

      <!-- Seção de informações adicionais -->
      <form id="criar-estagio">
        <div class="form-section">
          <h2>Autenticação</h2>
          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required />
          </div>
          <div class="form-group">
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" required />
          </div>
        </div>
        <div class="form-section">
          <h2>Informações de Estágio</h2>
          <div class="form-group">
            <label for="ira">IRA (Índice de Rendimento Acadêmico):</label>
            <input type="number" step="0.01" id="ira" name="ira" required />
          </div>
          <div class="form-group">
            <label for="cargaHorariaCumprida"
              >Carga Horária Cumprida (horas):</label
            >
            <input
              type="number"
              id="cargaHorariaCumprida"
              name="cargaHorariaCumprida"
              required
            />
          </div>
          <div class="form-group">
            <label for="primeiroEstagio">Primeiro Estágio:</label>
            <input
              type="checkbox"
              id="primeiroEstagio"
              name="primeiroEstagio"
            />
          </div>
          <div class="form-group">
            <label for="nomeEmpresa">Nome da Empresa:</label>
            <input type="text" id="nomeEmpresa" name="nomeEmpresa" required />
          </div>
          <div class="form-group">
            <label for="enderecoEmpresa">Endereço da Empresa:</label>
            <input
              type="text"
              id="enderecoEmpresa"
              name="enderecoEmpresa"
              required
            />
          </div>
          <div class="form-group">
            <label for="modalidade">Modalidade:</label>
            <select id="modalidade" name="modalidade" required>
              <option value="presencial">Presencial</option>
              <option value="remoto">Remoto</option>
              <option value="hibrido">Híbrido</option>
            </select>
          </div>
          <div class="form-group">
            <label for="cargaHorariaSemanal"
              >Carga Horária Semanal (horas):</label
            >
            <input
              type="number"
              id="cargaHorariaSemanal"
              name="cargaHorariaSemanal"
              required
            />
          </div>
          <div class="form-group">
            <label for="resumoAtividades">Resumo das Atividades:</label>
            <textarea
              id="resumoAtividades"
              name="resumoAtividades"
              rows="4"
              required
            ></textarea>
          </div>
          <div class="form-group">
            <label for="relacaoConteudo">Relação com Conteúdo Acadêmico:</label>
            <textarea
              id="relacaoConteudo"
              name="relacaoConteudo"
              rows="4"
              required
            ></textarea>
          </div>
          <div class="form-group">
            <label for="motivoIntencao">Motivo da Intenção:</label>
            <textarea
              id="motivoIntencao"
              name="motivoIntencao"
              rows="4"
              required
            ></textarea>
          </div>
          <button type="submit" class="submit-btn">Enviar</button>
        </div>
      </form>
    </div>
    <script>
      const form = document.getElementById("criar-estagio");

      form.addEventListener("submit", function(event) {
          event.preventDefault(); 

          const formData = new FormData(form);

          const toStringParams = new URLSearchParams(formData).toString();

          fetch("/dest-rural/without-service/criar-pedido-intencao-estagio?" + toStringParams, {
              method: "POST",
          })
          .then(response => {
              if (!response.ok) {
                  return response.json().then(data => {
                      alert(data.message);
                  });
              }
              return response.text().then(data => {
                  document.body.innerHTML = data;
              });
          })
          .catch(error => {
              alert("Erro ao enviar: " + error.message);
          });
      });
    </script>
  </body>
</html>
