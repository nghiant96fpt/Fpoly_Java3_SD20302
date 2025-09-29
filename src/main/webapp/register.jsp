<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
</head>
<body>
	<div class="container">
		<div class="col-6 offset-3">
			<form method="POST"
				action="${pageContext.request.contextPath}/register">
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Email</label>
				  <input value="${email}" name="email" type="text" class="form-control">
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Mật khẩu</label>
				  <input value="${password}" name="password" type="password" class="form-control">
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Họ và tên</label>
				  <input value="${name}" name="name" type="text" class="form-control">
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Giới tính</label>
				  <div class="form-check">
					  <input name="gender" ${gender == '0' ? 'checked' : ''} value="0" class="form-check-input" type="radio" id="radioDefault1">
					  <label class="form-check-label" for="radioDefault1">
					   	Nam
					  </label>
					</div>
					<div class="form-check">
					  <input name="gender" ${gender == '1' ? 'checked' : ''} value="1" class="form-check-input" type="radio" id="radioDefault2">
					  <label class="form-check-label" for="radioDefault2">
					   	Nữ
					  </label>
					</div>
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Số điện thoại</label>
				  <input value="${phone}" name="phone" type="text" class="form-control">
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Ngày sinh</label>
				  <input value="${birthDay}" name="birthDay" type="date" class="form-control">
				</div>
				
				<button type="submit" class="btn btn-primary">Đăng ký</button>
			</form>
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>