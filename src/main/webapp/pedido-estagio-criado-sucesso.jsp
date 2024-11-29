<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Estágio Criado com Sucesso</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px 40px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .container h1 {
            color: #28a745;
            margin-bottom: 20px;
        }
        .container p {
            margin: 10px 0;
        }
        .container .btn {
            margin-top: 20px;
            text-decoration: none;
            padding: 10px 20px;
            color: #fff;
            background-color: #28a745;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .container .btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Estágio Criado com Sucesso!</h1>
        <p><strong>Número do Pedido:</strong> ${numeroPedidoEstagio}</p>
        <p><strong>Nome do Discente:</strong> ${nomeDiscente}</p>
        <p><strong>ID do Discente:</strong> ${discenteId}</p>
        <p><strong>Empresa:</strong> ${nomeEmpresa}</p>
        <a href="index.jsp" class="btn">Voltar ao Início</a>
    </div>
</body>
</html>
