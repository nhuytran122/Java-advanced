<script>
  // Lấy thẻ input file và thẻ img preview
  const fileInput = document.getElementById('fileInput');
  const imagePreview = document.getElementById('imagePreview');

  fileInput.addEventListener('change', function (event) {
    const file = event.target.files[0]; // Lấy file đầu tiên
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        imagePreview.src = e.target.result; 
        imagePreview.style.display = 'block';
      };
      reader.readAsDataURL(file); 
    }
  });
</script>