

public class test {//密码用sha-256加密
    public static void main(String[] args){
        shaenc enc=new shaenc();
        System.out.println(enc.SHA("123","SHA-256"));
    }
}
