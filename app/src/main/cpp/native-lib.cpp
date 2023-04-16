#include <jni.h>
#include <string>
#include <vector>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_rechargerfacilement_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++ Nice Job!";
    return env->NewStringUTF(hello.c_str());
}


std::string Avertissement(){
    std::string avs="*#";
    return avs;
}

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_gtari_deltatechenologie_rechargerfacilement_MainActivity_GetPackage(
        JNIEnv* env,
        jobject /* this */,
        jint position,
        jstring package_name ) {
    std::vector<std::string>  IHM;

    std::string idd="tat";
    std::string idj="m.gt";
    std::string idn="char";
    std::string idza="nt";
    std::string idc="del";
    std::string idi="eche";
    std::string idb="ar";
    std::string ida="no";
    std::string idp="ger";
    std::string idl="ie.";
    std::string idh="log";
    std::string idf="co";
    std::string idz="eme";
    std::string idx="fa";
    std::string idg="i.";
    std::string idm="re";
    std::string idy="cil";


    jboolean isCopy;
    const char *package_name_converted= (env)->GetStringUTFChars(package_name, &isCopy);
    std::string idpackage=idf+idj+idb+idg+idc+idd+idi+ida+idh+idl+idm+idn+idp+idx+idy+idz+idza;
    if(package_name_converted ==  idpackage) {
        switch (position) {
            case 0 :
                IHM = {"101", "100", "124"};
                break;
            case 1 :
                IHM = {"100", "101", "122"};
                break;
            case 2 :
                IHM = {"123", "122", "140"};
                break;
            default:
                IHM = {"101", "100", "124"};
                break;

        }
    } else{
        IHM = {Avertissement()};
    }

    jclass clazz = (env)->FindClass("java/lang/String");
    jobjectArray objarray = (env)->NewObjectArray((jsize)IHM.size(), clazz , 0);
    for(int i = 0; i < IHM.size(); i++) {

        std::string s = IHM[i];

        jstring js = (env)->NewStringUTF(s.c_str());

        (env)->SetObjectArrayElement(objarray , i , js);

    }

    return objarray;
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_rechargerfacilement_CustomItemActivity_AvTitle(
        JNIEnv* env,
        jobject /* this */) {
    std::string title = "تحذير!";
    return env->NewStringUTF(title.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_rechargerfacilement_CustomItemActivity_AvMessge(
        JNIEnv* env,
        jobject /* this */) {
    std::string message = "لقد تمّ الكشف عن محاولة التلاعب بالتطبيق. أيّ تعدي على حقوق ملكيّة هذا التطبيق سيتمّ على إثرها غلق حسابك على متجر جوجل بلاي, ولن نتردد في تتبعك قضائيا وقانونيا و ذلك لتلاعب الواضح بصورتنا التجاريّة و التعدي على حقوق الملكية لهذا التطبيق.";
    return env->NewStringUTF(message.c_str());
}



extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_rechargerfacilement_CustomItemActivity_AvColorbckg(
        JNIEnv* env,
        jobject /* this */) {
    std::string bckg = "#ff0000";
    return env->NewStringUTF(bckg.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_rechargerfacilement_CustomItemActivity_AvC(
        JNIEnv* env,
        jobject /* this */) {
    std::string avc = "جميع الحقوق محفوظة ©";
    return env->NewStringUTF(avc.c_str());
}




extern "C"
JNIEXPORT jstring  JNICALL
Java_com_gtari_deltatechenologie_rechargerfacilement_MainActivity_GetAd(
        JNIEnv* env,
        jobject /* this */,
        jstring adb_name,
        jstring adi_name) {
    std::string av="";

    std::string idf = "ltateche";
    std::string idj = "c";
    std::string idb = "m.ex";
    std::string idg = "nolog";
    std::string idc = "amp";
    std::string idd = "le.de";
    std::string idi = "ta";
    std::string ida = "o";
    std::string idh = "ie.ti";

    jboolean isCopyb,isCopyi;
    const char *ad_name_convertedb = (env)->GetStringUTFChars(adb_name, &isCopyb);
    const char *ad_name_convertedi = (env)->GetStringUTFChars(adi_name, &isCopyi);
    std::string idAdb = "ca-app-pub-4168864559615120/1685190066";
    std::string idAdi = "ca-app-pub-4168864559615120/7867455038";
    if (ad_name_convertedb != idAdb || ad_name_convertedi != idAdi ) {
        av =Avertissement();
    }

    return env->NewStringUTF(av.c_str());
}


