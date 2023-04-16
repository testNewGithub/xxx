#include <jni.h>
#include <string>
<<<<<<< HEAD
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
=======
#include <array>



std::array<std::array<std::string, 2>, 45> arrayUSSDcodeOoredoo(std::array<std::array<std::string, 2>, 45> arr){

      /*******    Ooredoo USSD Code     *********/

                    //Mon Compte
    arr[0][0]="Consultation solde et bonus";       arr[0][1]="*100";
    arr[1][0]="suivi bonus SMS";                   arr[1][1]="*100*3";
    arr[2][0]="Suivi Bonus sur recharge";          arr[2][1]="*100*5";
    arr[3][0]="Suivi Bonus 100%";                  arr[3][1]="*100*7";
    arr[4][0]="Suivi Consommation";                arr[4][1]="*102";
    arr[5][0]="Appels manqués";                    arr[5][1]="*146";
    arr[6][0]="Transfert de solde";                arr[6][1]="*120";
    arr[7][0]="Code RIO Portabilité";              arr[7][1]="*172";
    arr[8][0]="Suivi Bonus Arja3 Ghodwa";          arr[8][1]="*197";
    arr[9][0]="Recharge de la ligne";         arr[9][1]="*101";

                  //Internet
    arr[10][0]="Forfaits internet";                arr[10][1]="*124";
    arr[11][0]="Suivi bonus data et international "
            "+ achat options /Business Progress";  arr[11][1]="*108";

                  //Appel
    arr[12][0]="OTLOBNI";                          arr[12][1]="*123";
    arr[13][0]="Tkallem / Dimanchez";              arr[13][1]="*132";
    arr[14][0]="Appels soir F&F";                  arr[14][1]="*133";

                 //International and Roaming
    arr[15][0]="Happy Zone";                       arr[15][1]="*125";
    arr[16][0]="Dhayafni en roaming";              arr[16][1]="*138";
    arr[17][0]="Forfaits internationaux";          arr[17][1]="*155";
    arr[18][0]="Roaming";                          arr[18][1]="*107";

                //SMS
    arr[19][0]="SMS Illimités";                     arr[19][1]="*131";
    arr[20][0]="SMS Max";                           arr[20][1]="*121";

                //Entertainment
    arr[21][0]="Samma3ni";                          arr[21][1]="*150";
    arr[22][0]="Otrobni/Otrobni+";                  arr[22][1]="*154";
    arr[23][0]="Starz Play";                        arr[23][1]="*182";

               //Offre / Forfait
    arr[24][0]="Programme LP";                      arr[24][1]="*111";
    arr[25][0]="Service Dhayefni";                  arr[25][1]="*113";
    arr[26][0]="Service Familia";                   arr[26][1]="*114";
    arr[27][0]="Nwimer";                            arr[27][1]="*118";
    arr[28][0]="Amigos";                            arr[28][1]="*119";
    arr[29][0]="Nessma";                            arr[29][1]="*4646";
    arr[30][0]="Offre 3jeja";                       arr[30][1]="*4444";
    arr[31][0]="Options INFINITO";                  arr[31][1]="*187";
    arr[32][0]="Kima T7eb";                         arr[32][1]="*222";
    arr[33][0]="Chila Bila";                        arr[33][1]="*200";
    arr[34][0]="Détails Offre & MSISDN &"
            " services renouvelables";              arr[34][1]="*177";
    arr[35][0]="Suivi gratuités Forfait Plus";      arr[35][1]="*106";

              //Migration
    arr[36][0]="Migration vers CA Mobile";          arr[36][1]="*1920";
    arr[37][0]="Migration vers ESS Mobile";         arr[37][1]="*1925";
    arr[38][0]="Migration vers CAB Mobile";         arr[38][1]="*1928";
    arr[39][0]="Migration";                         arr[39][1]="*140";

             //Divers
    arr[40][0]="MobiFlouss";                        arr[40][1]="*112";
    arr[41][0]="MobiCash";                          arr[41][1]="*122";
    arr[42][0]="Kridi";                             arr[42][1]="*188";
    arr[43][0]="Services Ooredoo";                  arr[43][1]="*145";
    arr[44][0]="Kidsafe";                           arr[44][1]="*178";



    return arr;

}





std::array<std::array<std::string, 2>, 28> arrayUSSDcodeOrange(std::array<std::array<std::string, 2>, 28> arr){

    /*******    Orange USSD Code     *********/

    //Mon Compte
    arr[0][0]="Suivi de la consommation";          arr[0][1]="*101";
    arr[1][0]="Suivi de la consommation du bonus "
            "pour les offres hybrides et postpayés";arr[1][1]="*103";
    arr[2][0]="Info Dispo (Menu "
            "de la messagerie vocale)";            arr[2][1]="*110";
    arr[3][0]="Portail des codes USSD";            arr[3][1]="*111";
    arr[4][0]="Gestion du programme de fidélité";  arr[4][1]="*112";
    arr[5][0]="Rechargez moi ";                    arr[5][1]="*114";
    arr[6][0]="Transfert de crédit ";              arr[6][1]="*116";
    arr[7][0]="Connaître votre numéro";            arr[7][1]="*123";
    arr[8][0]="Obtention du code RIO";             arr[8][1]="*172";
    arr[9][0]="Recharge de la ligne";              arr[9][1]="*100";

    //Internet
    arr[10][0]="Achat et suivi"
            " des options internet mobile";        arr[10][1]="*104";
    arr[11][0]="Paramétrage internet et MMS";      arr[11][1]="*121";
    arr[12][0]="Achat des options facebook ";      arr[12][1]="*124";
    arr[13][0]="Transférer internet";              arr[13][1]="*129";
    arr[14][0]="Achat des options data ";          arr[14][1]="*400";

    //Appel
    arr[15][0]="Rappelle-Moi";                     arr[15][1]="*115";
    arr[16][0]="SOS crédit";                       arr[16][1]="*122";

    //International and Roaming
    arr[17][0]="Achat des options internationales ";arr[17][1]="*127";
    arr[18][0]="Roaming data";                     arr[18][1]="*143";
    arr[19][0]="Best Roaming";                     arr[19][1]="*145";

    //Entertainment
    arr[20][0]="Menu de Music Play";               arr[20][1]="*144";


    //Offre / Forfait
    arr[21][0]="Options 7dith Ellil";              arr[21][1]="*105";
    arr[22][0]="Achat d’options voix ";            arr[22][1]="*120";

    //Divers
    arr[23][0]="Dima m3ak";                        arr[23][1]="*117";
    arr[24][0]="Service Bye Bye ";                 arr[24][1]="*118";
    arr[25][0]="Mobimoney ";                       arr[25][1]="*119";
    arr[26][0]="Orange Money";                     arr[26][1]="*139";
    arr[27][0]="Suivi de vos comptes"
            " depuis votre mobile";                arr[27][1]="*147";



    return arr;

}


std::array<std::array<std::string, 2>, 58> arrayUSSDcodeTunisieTelecom(std::array<std::array<std::string, 2>, 58> arr){

    /*******    Tunisie Télécom USSD Code     *********/

    //Mon Compte
    arr[0][0]="Votre numéro d’appel et offre";     arr[0][1]="*146";
    arr[1][0]="Recharge de la ligne";              arr[1][1]="*123";
    arr[2][0]="Consultation solde et bonus";       arr[2][1]="*122";
    arr[3][0]="suivi bonus corporate";             arr[3][1]="*122*3";
    arr[4][0]="Menu pour la gestion de "
            "tous les services utiles";            arr[4][1]="*100";
    arr[5][0]="Service MMS";                       arr[5][1]="*110";
    arr[6][0]="Gestion de mon programme"
            " de fidélité Kélma";                  arr[6][1]="*111";
    arr[7][0]="SOS Solde";                         arr[7][1]="*150";
    arr[8][0]="Demande du code PUK";               arr[8][1]="*153*3";
    arr[9][0]="RIO Portabilité";                   arr[9][1]="*172";
    arr[10][0]="Options++";                        arr[10][1]="*211";


    //Internet
    arr[11][0]="suivi du forfait internet mobile";  arr[11][1]="*122*2";
    arr[12][0]="Activation internet mobile";        arr[12][1]="*140*0";
    arr[13][0]="Portail WAP: wap.ahaya.tn";         arr[13][1]="*120";
    arr[14][0]="Service Black Berry";               arr[14][1]="*109";
    arr[15][0]="suivi forfait Black Berry";         arr[15][1]="*122*8";
    arr[16][0]="Data_Packages";                     arr[16][1]="*145";
    arr[17][0]="Forfait Facebook";                  arr[17][1]="*182";
    arr[18][0]="3G_provisioning_HUR";               arr[18][1]="*143";

    //Appel
    arr[19][0]="Activation double appel";           arr[19][1]="*43";
    arr[20][0]="Activation appels manqués";         arr[20][1]="*62*1162";
    arr[21][0]="Appel en conférence (Gratuit)";     arr[21][1]="*115";

    //International and Roaming
    arr[22][0]="Forfait_International "
            "(Forfait Passeport )";                 arr[22][1]="*108";
    arr[23][0]="Activation Roaming Prépayé";        arr[23][1]="*117*1";
    arr[24][0]="Service SMART roaming";             arr[24][1]="*147";

    // Fixe
    arr[25][0]="Recharge_Fixe";                     arr[25][1]="*132";

    //Entertainment
    arr[26][0]="ICFLIX";                            arr[26][1]="*210";
    arr[27][0]="TTStreamning";                      arr[27][1]="*209";
    arr[28][0]="MOUZIKTI (RBT)";                    arr[28][1]="*208";

    //Offre/Forfait
    arr[29][0]="Offre 1DT = 40min";                 arr[29][1]="*106";
    arr[30][0]="New soir";                          arr[30][1]="*107";
    arr[31][0]="10mn Cons 30mn (Day pass )";        arr[31][1]="*116";
    arr[32][0]="Forfait Messagét";                  arr[32][1]="*118";
    arr[33][0]="Activation DOUBLE";                 arr[33][1]="*130*1";
    arr[34][0]="Activation BINETNA";                arr[34][1]="*130*2";
    arr[35][0]="Activation BEST";                   arr[35][1]="*130*3";
    arr[36][0]="Suivi consommation BEST";           arr[36][1]="*125*1";
    arr[37][0]="Activation SIGOUNDA";               arr[37][1]="*130*6";
    arr[38][0]="ESS_1000";                          arr[38][1]="*197";
    arr[39][0]="Offres partenariat KELMA";          arr[39][1]="*212";
    arr[40][0]="Mriguel";                           arr[40][1]="*214";
    arr[41][0]="elissa_300";                        arr[41][1]="*129";

    //Migration
    arr[42][0]="migration vers 1000TM(ancien client)";  arr[42][1]="*161";
    arr[43][0]="migration vers 1000CSS(ancien client)"; arr[43][1]="*162";
    arr[44][0]="migration vers Trankil Elissa";         arr[44][1]="*205";
    arr[45][0]="migration TT verts TM (taraji mobile)"; arr[45][1]="*202";
    arr[46][0]="migratioin de 1000%TT vers Trankil TT"; arr[46][1]="*201";

   //Divers
    arr[47][0]="Suivi forfait Messagét";                    arr[47][1]="*122*9";
    arr[48][0]="SMS « Kallemni » restants";                 arr[48][1]="*124*1";
    arr[49][0]="Elissa Transfer money v3";                  arr[49][1]="*128";
    arr[50][0]="Call me Ellissa";                           arr[50][1]="*131";
    arr[51][0]="MobiRacid, Transfert du montant";           arr[51][1]="*133";
    arr[52][0]="Mobiracid_Telecom";                         arr[52][1]="*144";
    arr[53][0]="Service Tabba3ni /"
            " TT Follow Me USSD Service";                   arr[53][1]="*149";
    arr[54][0]="DUO  (Numéro virtuel)";                     arr[54][1]="*180";
    arr[55][0]="MOBI+  PoP (FAF)";                          arr[55][1]="*189";
    arr[56][0]="Service Mobidinar (gratuit)";               arr[56][1]="*104";
    arr[57][0]="Option Libye – Algérie";                    arr[57][1]="*203";

    return arr;

}







std::array<std::array<std::string, 2>, 45> arrayUSSDcodeOoredooAr(std::array<std::array<std::string, 2>, 45> arr){

    /*******    Ooredoo USSD Code     *********/

    //Mon Compte
    arr[0][0]="الإطلاع على الرصيد و الإضافات المتبقية ";       arr[0][1]="*100";
    arr[1][0]="الإطلاع على رسائل SMS الإضافيّة";                   arr[1][1]="*100*3";
    arr[2][0]="الإضافات على الشحن";          arr[2][1]="*100*5";
    arr[3][0]="الإطلاع على %100 هدايا و إضافات";                  arr[3][1]="*100*7";
    arr[4][0]="تتبع الإستهلاك";                arr[4][1]="*102";
    arr[5][0]="المكالمات الفائتة";                    arr[5][1]="*146";
    arr[6][0]="تحويل الرصيد";                arr[6][1]="*120";
    arr[7][0]="كود RIO لتحويل";              arr[7][1]="*172";
    arr[8][0]="الإطلاع على هدايا و إضافات عرض أرجع غدوة";          arr[8][1]="*197";
    arr[9][0]="شحن الرصيد";         arr[9][1]="*101";

    //Internet
    arr[10][0]="الإشتراك في خدمة الأنترنت";                arr[10][1]="*124";
    arr[11][0]="الإطلاع على إضافات داتا والدولي + خيارات الشراء/ تنمية الأعمال";  arr[11][1]="*108";

    //Appel
    arr[12][0]="أُطلبني";                          arr[12][1]="*123";
    arr[13][0]="تكلّم / ديمونشي";              arr[13][1]="*132";
    arr[14][0]=" إتصالات F&F الليلية";                  arr[14][1]="*133";

    //International and Roaming
    arr[15][0]="هـبِّـــي زون";                       arr[15][1]="*125";
    arr[16][0]="ضيّفني في سفرة";              arr[16][1]="*138";
    arr[17][0]="عروض دوليّة";          arr[17][1]="*155";
    arr[18][0]="سفرة";                          arr[18][1]="*107";

    //SMS
    arr[19][0]="إرساليات SMS غير محدودة";                     arr[19][1]="*131";
    arr[20][0]="إرساليات SMS ماكس";                           arr[20][1]="*121";

    //Entertainment
    arr[21][0]="سامعني";                          arr[21][1]="*150";
    arr[22][0]="أطربني/أطربني+";                  arr[22][1]="*154";
    arr[23][0]="ستارز بلاي";                        arr[23][1]="*182";

    //Offre / Forfait
    arr[24][0]="برنامج LP";                      arr[24][1]="*111";
    arr[25][0]="خدمة ضايفني";                  arr[25][1]="*113";
    arr[26][0]="خدمة فاميليا";                   arr[26][1]="*114";
    arr[27][0]="دبّر نويمر";                            arr[27][1]="*118";
    arr[28][0]="أميڨوس";                            arr[28][1]="*119";
    arr[29][0]="نسمة موبيل";                            arr[29][1]="*4646";
    arr[30][0]="عرض عجاجة";                       arr[30][1]="*4444";
    arr[31][0]="خيارات أنفينيتو";                  arr[31][1]="*187";
    arr[32][0]="كيما تحب";                         arr[32][1]="*222";
    arr[33][0]="شيلا بيلا";                        arr[33][1]="*200";
    arr[34][0]="خيارات العروض & MSISDN &"
            " الخدمات المتجددة";              arr[34][1]="*177";
    arr[35][0]="الإطلاع على مجانيّة عرض بلاس";      arr[35][1]="*106";

    //Migration
    arr[36][0]="التحويل إلى النادي الإفريقي موبيل";          arr[36][1]="*1920";
    arr[37][0]="التحويل إلى النجم الساحلي موبيل";         arr[37][1]="*1925";
    arr[38][0]="التحويل إلى النادي البنزرتي موبيل";         arr[38][1]="*1928";
    arr[39][0]="تحويل";                         arr[39][1]="*140";

    //Divers
    arr[40][0]="مُبيفلوس";                        arr[40][1]="*112";
    arr[41][0]="مُبيكاش";                          arr[41][1]="*122";
    arr[42][0]="كريدي";                             arr[42][1]="*188";
    arr[43][0]="خدمات أوريدو";                  arr[43][1]="*145";
    arr[44][0]="حماية للأطفال";                           arr[44][1]="*178";



    return arr;
}


//Orange

std::array<std::array<std::string, 2>, 28> arrayUSSDcodeOrangeAr(std::array<std::array<std::string, 2>, 28> arr){

    /*******    Orange USSD Code     *********/

    //Mon Compte
    arr[0][0]="الإطلاع على الرصيد";          arr[0][1]="*101";
    arr[1][0]="الإطلاع على إستهلاك الإضافات لعروض hybrides و postpayés";arr[1][1]="*103";
    arr[2][0]="المعلومات المتاحة (قائمة الرسائل الصوتية)";            arr[2][1]="*110";
    arr[3][0]="إدارة برنامج Fidélité";  arr[3][1]="*112";
    arr[4][0]="إرسال طلب شحن الرصيد";                    arr[4][1]="*114";
    arr[5][0]="تحويل الرصيد ";              arr[5][1]="*116";
    arr[6][0]="تعرف على رقمك";            arr[6][1]="*123";
    arr[7][0]="الحصول على رمز RIO";             arr[7][1]="*172";
    arr[8][0]="بوابة رموز USSD";                   arr[8][1]="*111";
    arr[9][0]="شحن الرصيد";              arr[9][1]="*100";

    //Internet
    arr[10][0]="الشراء و الإطلاع على خيارات أنترنت موبيل";        arr[10][1]="*104";
    arr[11][0]="الحصول على إعدادات الإنترنت و MMS";      arr[11][1]="*121";
    arr[12][0]=" خيارات الشراء فيسبوك";      arr[12][1]="*124";
    arr[13][0]="إرسال الإنترنت";              arr[13][1]="*129";
    arr[14][0]=" خيارات شراء داتا";          arr[14][1]="*400";

    //Appel
    arr[15][0]="إرسال ذكرني من فضلك";                     arr[15][1]="*115";
    arr[16][0]="الإستنجاد برصيد إضافي";                       arr[16][1]="*122";

    //International and Roaming
    arr[17][0]="شراء الخيارات الدولية";arr[17][1]="*127";
    arr[18][0]="سفرة مع داتا";                     arr[18][1]="*143";
    arr[19][0]="أحسن سفرة";                     arr[19][1]="*145";

    //Entertainment
    arr[20][0]="قائمة تشغيل الأغاني";               arr[20][1]="*144";


    //Offre / Forfait
    arr[21][0]="خيارات حديث الليل";              arr[21][1]="*105";
    arr[22][0]="شراء الخيارات الصوتية";            arr[22][1]="*120";

    //Divers
    arr[23][0]="ديما معاك";                        arr[23][1]="*117";
    arr[24][0]="خدمة باي باي";                 arr[24][1]="*118";
    arr[25][0]="موبي فلوس";                       arr[25][1]="*119";
    arr[26][0]="أرونج فلوس";                     arr[26][1]="*139";
    arr[27][0]="الإطلاع على حساباتك من هاتفك المحمول";                arr[27][1]="*147";



    return arr;
}



std::array<std::array<std::string, 2>, 58> arrayUSSDcodeTunisieTelecomAr(std::array<std::array<std::string, 2>, 58> arr){

    /*******    Tunisie Télécom USSD Code     *********/

    //Mon Compte
    arr[0][0]="رقمك والعرض الخاص بخطك";     arr[0][1]="*146";
    arr[1][0]="الإطلاع على الرصيد";       arr[1][1]="*122";
    arr[2][0]="الإطلاع على إضافات و هدايا الشركات";             arr[2][1]="*122*3";
    arr[3][0]="قائمة الإطلاع على جميع الخدمات المتاحة";            arr[3][1]="*100";
    arr[4][0]="خدمات MMS";                       arr[4][1]="*110";
    arr[5][0]="التصرف في برنامج الحريف الوفي كلمة";                  arr[5][1]="*111";
    arr[6][0]="الإستنجاد برصيد إضافي";                         arr[6][1]="*150";
    arr[7][0]="طلب الرمز PUK";               arr[7][1]="*153*3";
    arr[8][0]="بوابة RIO";                   arr[8][1]="*172";
    arr[9][0]="خيارات ++";                        arr[9][1]="*211";
    arr[10][0]="شحن الرصيد";              arr[10][1]="*123";


    //Internet
    arr[11][0]="الإطلاع على عرض أنترنت موبيل";  arr[11][1]="*122*2";
    arr[12][0]="تفعيل الأنترنت موبيل";        arr[12][1]="*140*0";
    arr[13][0]="بواب wap.ahaya.tn :WAP";         arr[13][1]="*120";
    arr[14][0]="خدمة Black Berry";               arr[14][1]="*109";
    arr[15][0]="الإطلاع على عرض Black Berry";         arr[15][1]="*122*8";
    arr[16][0]="حزم البيانات";                     arr[16][1]="*145";
    arr[17][0]="إشتراك الفيسبوك";                  arr[17][1]="*182";
    arr[18][0]="التزويد_الحر_3G";               arr[18][1]="*143";

    //Appel
    arr[19][0]="تفعيل الإتصال المزدوج";           arr[19][1]="*43";
    arr[20][0]="تفعيل التنبيه بالإتصالات المفقودة";         arr[20][1]="*62*1162";
    arr[21][0]="الإتصال أثناء المحاضرة (مجاني)";     arr[21][1]="*115";

    //International and Roaming
    arr[22][0]="الإشتراك الدولي (عرض جواز سفر)";                 arr[22][1]="*108";
    arr[23][0]="تفعيل تجوال مسبق الدفع";        arr[23][1]="*117*1";
    arr[24][0]="خدمة التجوال الذكي";             arr[24][1]="*147";

    // Fixe
    arr[25][0]="الشحن الثابت";                     arr[25][1]="*132";

    //Entertainment
    arr[26][0]="آي سي فلكس";                            arr[26][1]="*210";
    arr[27][0]="البث المتواصل_TT";                      arr[27][1]="*209";
    arr[28][0]="موزيكتي(RBT)";                    arr[28][1]="*208";

    //Offre/Forfait
    arr[29][0]="عرض 1د= 40دقيقة";                 arr[29][1]="*106";
    arr[30][0]="ليلة جديدة";                          arr[30][1]="*107";
    arr[31][0]="10 دقائق إستهلاك 30دقيقة (صالح ليوم )";        arr[31][1]="*116";
    arr[32][0]="عرض الرسائل";                  arr[32][1]="*118";
    arr[33][0]="التفعيل الثنائي";                 arr[33][1]="*130*1";
    arr[34][0]="تفعيل بينتنا";                arr[34][1]="*130*2";
    arr[35][0]="تفعيل أحسن ";                   arr[35][1]="*130*3";
    arr[36][0]="الإطلاع على إستهلاك أحسن";           arr[36][1]="*125*1";
    arr[37][0]="تفعيل سڨوندا";               arr[37][1]="*130*6";
    arr[38][0]="النجم الساحلي _1000";                          arr[38][1]="*197";
    arr[39][0]="عروض الشراكة كلمة";          arr[39][1]="*212";
    arr[40][0]="مريڨل";                           arr[40][1]="*214";
    arr[41][0]="عليسة_300";                        arr[41][1]="*129";

    //Migration
    arr[42][0]="التحويل إلى 1000TM(حريف قديم)";  arr[42][1]="*161";
    arr[43][0]="التحويل إلى 1000CSS(حريف قديم)"; arr[43][1]="*162";
    arr[44][0]="التحويل إلى ترونكيل عليسة";         arr[44][1]="*205";
    arr[45][0]="التحويل من  TT إلى TM (ترجي موبيل)"; arr[45][1]="*202";
    arr[46][0]="التحويل من %1000 TT  إلى ترونكيل TT"; arr[46][1]="*201";

    //Divers
    arr[47][0]="الإطلاع على عرض الرسائل";                    arr[47][1]="*122*9";
    arr[48][0]="إرساليات SMS « كلمني  » المتبقية";                 arr[48][1]="*124*1";
    arr[49][0]="عليسة تحويل الأموال v3";                  arr[49][1]="*128";
    arr[50][0]="كلمني عليسة";                           arr[50][1]="*131";
    arr[51][0]="موبي رصيد, تحويل المبلغ";           arr[51][1]="*133";
    arr[52][0]="موبي رصيد تليكوم";                         arr[52][1]="*144";
    arr[53][0]= "خدمة تبعني / TT خدمة USSD تبعني";                           arr[53][1]="*149";
    arr[54][0]="الثنائي  (رقم وهمي)";                     arr[54][1]="*180";
    arr[55][0]="موبي + (PoP(FAF";                          arr[55][1]="*189";
    arr[56][0]="خدمة موبي دينار (مجاني)";               arr[56][1]="*104";
    arr[57][0]="خيار ليبيا - الجزائر";                    arr[57][1]="*203";

    return arr;

}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_elkolxelkol_CustomItemActivity_AvTitle(
>>>>>>> Initial commit
        JNIEnv* env,
        jobject /* this */) {
    std::string title = "تحذير!";
    return env->NewStringUTF(title.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
<<<<<<< HEAD
Java_com_gtari_deltatechenologie_rechargerfacilement_CustomItemActivity_AvMessge(
=======
Java_com_gtari_deltatechenologie_elkolxelkol_CustomItemActivity_AvMessge(
>>>>>>> Initial commit
        JNIEnv* env,
        jobject /* this */) {
    std::string message = "لقد تمّ الكشف عن محاولة التلاعب بالتطبيق. أيّ تعدي على حقوق ملكيّة هذا التطبيق سيتمّ على إثرها غلق حسابك على متجر جوجل بلاي, ولن نتردد في تتبعك قضائيا وقانونيا و ذلك لتلاعب الواضح بصورتنا التجاريّة و التعدي على حقوق الملكية لهذا التطبيق.";
    return env->NewStringUTF(message.c_str());
}



extern "C"
JNIEXPORT jstring JNICALL
<<<<<<< HEAD
Java_com_gtari_deltatechenologie_rechargerfacilement_CustomItemActivity_AvColorbckg(
=======
Java_com_gtari_deltatechenologie_elkolxelkol_CustomItemActivity_AvColorbckg(
>>>>>>> Initial commit
        JNIEnv* env,
        jobject /* this */) {
    std::string bckg = "#ff0000";
    return env->NewStringUTF(bckg.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
<<<<<<< HEAD
Java_com_gtari_deltatechenologie_rechargerfacilement_CustomItemActivity_AvC(
=======
Java_com_gtari_deltatechenologie_elkolxelkol_CustomItemActivity_AvC(
>>>>>>> Initial commit
        JNIEnv* env,
        jobject /* this */) {
    std::string avc = "جميع الحقوق محفوظة ©";
    return env->NewStringUTF(avc.c_str());
}




<<<<<<< HEAD
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

=======


//Return Ooredoo USSD Codes
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_elkolxelkol_MainActivity_stringFromJNIooredoo(
        JNIEnv *env,
        jobject /* this */,
        jint i,
        jint j) {
    std::string test = "null";
    std::array<std::array<std::string, 2>, 45> arrUSSD;

    if(arrUSSD[0][0]== "\0")
        arrUSSD=arrayUSSDcodeOoredoo(arrUSSD);

    return env->NewStringUTF(arrUSSD[i][j].c_str());
};
// Arabic
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_elkolxelkol_MainActivity_stringFromJNIooredooAr(
        JNIEnv *env,
        jobject /* this */,
        jint i,
        jint j) {
    std::string test = "null";
    std::array<std::array<std::string, 2>, 45> arrUSSD;

    if(arrUSSD[0][0]== "\0")
        arrUSSD=arrayUSSDcodeOoredooAr(arrUSSD);

    return env->NewStringUTF(arrUSSD[i][j].c_str());
};



//Return Orange USSD Codes
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_elkolxelkol_MainActivity_stringFromJNIorange(
        JNIEnv *env,
        jobject /* this */,
        jint i,
        jint j) {
    std::string test = "null";
    std::array<std::array<std::string, 2>, 28> arrUSSD;

    if(arrUSSD[0][0]== "\0")
        arrUSSD=arrayUSSDcodeOrange(arrUSSD);

    return env->NewStringUTF(arrUSSD[i][j].c_str());
};
// Orange Arabic USSD
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_elkolxelkol_MainActivity_getPackageAppsName(
        JNIEnv* env,
        jobject /* this */) {
    std::string avc = "com.gtari.deltatechenologie.elkolxelkol";
    return env->NewStringUTF(avc.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_elkolxelkol_MainActivity_stringFromJNIorangeAr(
        JNIEnv *env,
        jobject /* this */,
        jint i,
        jint j) {
    std::string test = "null";
    std::array<std::array<std::string, 2>, 28> arrUSSD;

    if(arrUSSD[0][0]== "\0")
        arrUSSD=arrayUSSDcodeOrangeAr(arrUSSD);

    return env->NewStringUTF(arrUSSD[i][j].c_str());
};



//Return Tunisie Télécom USSD Codes
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_elkolxelkol_MainActivity_stringFromJNItunisieTelecom(
        JNIEnv *env,
        jobject /* this */,
        jint i,
        jint j) {
    std::string test = "null";
    std::array<std::array<std::string, 2>, 58> arrUSSD;

    if(arrUSSD[0][0]== "\0")
        arrUSSD=arrayUSSDcodeTunisieTelecom(arrUSSD);

    return env->NewStringUTF(arrUSSD[i][j].c_str());
};
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_elkolxelkol_OtherApplications_getPackageAppName(
        JNIEnv *env,
        jobject /* this */
) {
    std::string test = "com.gtari.deltatechenologie.tetromino";

    return env->NewStringUTF(test.c_str());
};
// Arabic
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gtari_deltatechenologie_elkolxelkol_MainActivity_stringFromJNItunisieTelecomAr(
        JNIEnv *env,
        jobject /* this */,
        jint i,
        jint j) {
    std::string test = "null";
    std::array<std::array<std::string, 2>, 58> arrUSSD;

    if(arrUSSD[0][0]== "\0")
        arrUSSD=arrayUSSDcodeTunisieTelecomAr(arrUSSD);

    return env->NewStringUTF(arrUSSD[i][j].c_str());
};






>>>>>>> Initial commit

