<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AccountBalance</title>
    <style>
        table {
            margin: 0 auto;
        }

        td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td>상품명</td>
        <td>${accountBalance.productName}</td>
    </tr>
    <tr>
        <td>계좌잔액</td>
        <td>${accountBalance.balanceAmt}</td>
    </tr>
    <tr>
        <td>최종거래일</td>
        <td>${accountBalance.lastTranDate}</td>
    </tr>
</table>
</body>
</html>
