$(document).ready(function (){
    $(".submit").click(function (){
        var email = $(".email").val();
        var role = 2
        if($(".roleUser").prop("checked")){
            role = 1
        }
        var gender = 'F'
        if($(".genderMale").prop("checked")){
            gender = 'M'
        }
        var firstName = $(".firstName").val();
        var lastName = $(".lastName").val();
        var dateOfBirth = $(".dateOfBirth").val();
        var photo = $(".photo").attr("src");
        var phone = $(".phone").val();
        var address = $(".address").val();
        var param = "email="+email+"&firstName="+firstName+"&lastName="+lastName+
            "&dateOfBirth="+dateOfBirth+"&phone="+phone+"&photo="+photo+
            "&address="+address+"&gender="+gender+"&roleId="+role;
        console.log(param)
        $.ajax({
            url:'http://localhost:8080/SunshineAirlines/addUser',
            type:'post',
            data:param,
            success:function(msg){
                var json = JSON.parse(msg);
                if(json.flag=="success"){
                    location.href="UserManagement.html"
                }else{
                    alert(json.data);
                }
            }
        })
    })
    $(".upload-input").change(function (){
        var files = this.files[0]
        var reader =new FileReader()
        reader.onload =function (even){
            $(".photo").attr("src",even.target.result)
            console.log(even.target.result)
        }
        reader.readAsDataURL(files)
    })

    $(".cancel").click(function (){
        location.href="UserManagement.html"
    })
})