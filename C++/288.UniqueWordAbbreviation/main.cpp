#include <map>
#include <iostream>
#include <vector>

using namespace std;

class ValidWordAbbr {
    
public:
	ValidWordAbbr(vector<string> &dictionary);
	~ValidWordAbbr();
    bool isUnique(string word);
private:
    multimap<string,string> dic;
};/*End of class Solution */

ValidWordAbbr::~ValidWordAbbr(){
}

ValidWordAbbr::ValidWordAbbr(vector<string> &dictionary) {
    multimap<string, string>::iterator it;
    pair <std::multimap<string, string>::iterator, std::multimap<string, string>::iterator> range;
    int i;
    string word;
    string middle;
    string key;
    
    for(i = 0; i < dictionary.size(); ++i){
        word = dictionary[i];
		key.clear();
        if(word.size() > 2){
            middle =  to_string(word.size() - 2);
            key.push_back(word[0]);
            key.append(middle);
            key.push_back(word[word.size() - 1]);
        }
        else{
            key = word;
        }
        
        range = dic.equal_range(key);

		cout << "key: " << key <<", " << "word: " << word << endl;
        
        for (it=range.first; it != range.second; ++it){
            if(it->second.compare(word)  == 0){
                break;
            }
        }
        
        if(it == range.second){
            dic.insert(make_pair(key, word));
        }
    }
}

bool ValidWordAbbr::isUnique(string word) {
    string abb;
    string middle;
    int count;
    multimap<string, string>::iterator it;
    
    if(word.size() > 2){
        middle =  to_string(word.size() - 2);
        abb.push_back(word[0]);
        abb.append(middle);
        abb.push_back(word[word.size() - 1]);
    }
    else{
        abb = word;
    }
    
    if(dic.count(abb) > 1){
        return false;
    }
    
    it = dic.find(abb);
    if(it != dic.end()){
        if(it->second.compare(word) == 0){
            return true;
        }
        else{
            return false;
        }
    }
    else{
        return true;
    }
}

int main()
{	
	vector<string> dic;
	int size;
	string strs[] = {"nivhmv","kttbl","ifhojravrrtpqh","nlvgtpubiiewabuvuj","baffhlcbz","jqiqiqxzbaovy","aebbtzexklfxvlov","zcfhv","njirmkwldncktq","rfuqtqkkacyjwcsa","jnd","rzhcpxvausudzsogutcr","uikadpfoiropkloxjmvg","ixaymcqpzyzks","gjcnzawkhhyivluqkah","albqwvwbivolgsyofc","xjoznvnvd","eilcxnit","yclxquuz","fdjlzhcmbphwrw","ygznbuneuke","ipwaizhmatixavdmonj","qrwgiymlnbzkj","hdmsskespazfijlwjlka","eoqknjpvfuxoyscxa","erkbdrvzaonhrki","iquaqzugoqrr","bohfhlswvceftf","bgrgwhlwlzdxtex","artu","qudbostriuivgfs","oyflmcl","mikc","qzywrevphfillz","rsdpponepgfbfzdtsaw","tqolocwyt","jzfzdlqeikehokgsz","wyudkqx","mvsycngshtlkxjc","cyofrmicwn","zoluzscd","imobkkmrukuwrirrl","zxvfsujrasu","bwwgsmuyaawfni","bjzkmjkxlduiz","qwosx","cwzaxtdduvlig","jtxchu","kclerbxlbml","zebkavaibiwaphoxyoh","gspqfjsnlmkbtihj","qpx","womsj","evtvlgoyfptfgnigr","ondtvxlulecn","whjwguqdfoplm","cckfaptxdgek","odux","eytnkiaefzugwnilyxko","youwbpzkdhlpghrdnwtb","lqwvndawtnaiglogcg","yzrqlvdmgt","tdcuhjaofq","suiaeqtybweuzfc","vxolgyyjnhnbdqzzklvy","aocvijnmdkylfc","dhzpcaikhabnixsu","ncabm","pizac","veat","qsziglgddotcdinzwk","rcw","mxrnwbkbxl","tennwtwycnzsbcx","tbgouonbnxfpsysug","xlfzdcosyesadnolay","azymzwmuyghvhavwnnh","mdrungpadqnydgl","giojcurlerfva","hkzzpifti","mhdpbtoplcwmupuvvfaz","djfwahtddy","frsvaiwdkfrqukaoztac","fhjt","gxfv","jnvyrltiwki","cdxxnnhvzxu","poh","cdidnxtnxg","nacodnabt","ixeppblduxfhdlmtfch","qfszkdj","ruafjitnmdkvycoie","gewlbzphcmkxwletfb","svfvklevnw","uwgsfu","zbclbplprfexf","efwbdneiky","gogvhawvmbigpus","ctyyozgltiiefhetmh","aukjtwaiyw","wpfccljmuovnwz","ornddkzoxuyz","pmvzcrwiee","jgawchlytmm","ylxidmabgcnlazsx","ymjis","ytkddulcsweiwfrhj","czmpwv","gsodruixdtymm","nfsng","twejonvvgeuehmowln","dzjsrm","ljltgavazh","sph","tlzecvqjbbqh","quqcoqiex","ouprcjnymolmxynhof","fkanwxsymzudjsc","yxccqsudrfqntdr","vburxte","cqfivgm","mcfnukitmncwjra","irehsbzymtucyfsuu","xipyfpynaii","lrhn","usxuwu","flinru","qdkmrnfuscexnca","yspe","ots","ccinjjf","einxgdrgoszispsw","xgswdpnuxptdp","lznavnvkkrn","ngkcefdmdz","udbarlosrwp","gxlopuohrr","rzlnmubaekbqj","wwsvbglisn","jiv","emynrlvjrfxgultioyls","onvqpfqiiotezwdt","mqaikzwohthehvpti","sznjhfotmjaksfuhze","bojqcswphibtb","urpdjaxiqzbyjcp","byjbmhqfiywzhdwh","dqfgdsofracrasazawk","cqpscdjpgfdcilbpkl","nfuooqihmgnopy","xzfagxnflzlsmatey","tuhue","hmvaccxhirvbiyfzquq","emijncfedprzvw","akd","cruqhs","zfsnmlchxxuurda","wmsqzyuiymswsoxvz","qgxpneeocyghhxnym","dzy","bekup","miiqoyocrunawg","nmvbaycvlfatyxzitov","khjrsuprgucdkp","qogz","zvensgzbskuobime","cxjgpvegxpqdbesy","douqggkdbc","kqbdplapxhbafmxuudm","bnmcynlkdggnhhvo","tva","azqsuxo","bcwj","glskkxemrbsppl","qyyplbcfocfvdrffrcl","fdm","suazkpkknkobj","dshpyxfxces","fiolyzgsoabhmaan","vrc","wrl","cawedhemlltfnit","mjwqojthrjhw","oryyyjtoqedyqjcdao","ynsaffcx","yxuaxipsfesq","iaiwtcwhvuhrq","sicgldkohxgf","icmbyoflxu","zpbshiudqdlgdngh","lstd","opmefbdcdgpwjagz","dozd","qodrshhgh","eqvlkvrmqz","vcc","nxsnhksagxntqvty","unarpttvlqcpnj","jgob","qrzqkdvfnghk","nijyouxxnmr","rrkhepubirxaazve","lun","qjewfglypcmyiqf","dzjb","gtwkwfhhoiddkzjfd","dbdmiluppifwotryioq","vlxvdhuhsfjzdjflpr","vnrpvrmpwirurucf","onwyxktvpjyajxjw","rpwnvmtiegdvruiaz","ktbvuspy","qycerqupgrvrramnx","kodpelcuefwyjxasx","bfmb","dcsowxhvulasygvr","dumaycliwxywb","lsnyfwarfhpk","pzblhlhsykbrl","yttvkvpkfqqc","shllbwktyaynwvxaxp","afijlkimbnz","fekxgjgnrx","nwmitjufbaazrgpgq","uinln","qssnbf","jbtmkvrdrlaaxr","xpz","deyv","xnputswrzozsmjxujve","ucxpxdyirzyhbin","jxzvtglv","dxxvtabrphwkx","mvwuxj","rkmctbmbtryrieqq","tqegjzsdkdkvbmyej","wbppcmhxeqqmffaq","yhhzxk","rsbudhigzxlrwkxvez","sjcbggwup","qwdsnhznnrnvsn","tzywwhybhq","neultdpsyrpqhfkdov","ggefbmxnlm","npsrzupdhfsyyboscq","yadeexsfbvlw","igniphssxsmz","mohujksnsqlwrkl","eqjogjezmdkqxk","hunbzdolfcn","jdjkrxptgzxiu","iraoinbsdu","nwqc","jbmrpzvplwup","cxgrnekzwj","ukzxuncfn","idmsiifvbsvtprap","fawlfn","owgqfcof","wplbakweyaiiahzkj","pksv","gjayfprsxbksnns","wxbvtdslarjts","paizxbbjxapucowu","lwcvgskmez","abcsmmfxitsdi","tniwfgomkxulqtcb","ajlidpmn","imtvqdgyhx","fmxnusohzpvtx","zwltptnpyvy","mflwvblaux","jprnzhprny","adtvkajbs","gndyniqnf","vyuepme","mjyhqtqcsccnqfoms","yxvfdekka","utkvobwhb","rqkgtpgqdcwfftm","bedz","xrblbiztxnok","yiywc","olypwr","cxecbiuuojyxmnk","vykhagimgwnre","umvondyp","crpsideylddjh","oecnlgovob","amywx","fwcyecufzcyzfnpmuzz","msbqjjcxz","focymtnvovlcsdidk","cgfhtpfa","zcsidwsxmmcppciqzf","ftzbckqf","ihugmxzlfdabwsns","jqvbmidivahkwre","fmhc","vyjdmouzkkdbmjlhmgk","jzjjerotirqmannam","fhweiawutgqbr","qnnmjlrecrxzzfm","hycjsureee","gmsdpbsoqkbvvzdac","axvwzamvmawvkr","ncact","cfecwhbzssei","javbviqsxsyyzlcilr","xwrhixryq","kzdoaxpdfsyvnpveq","vrmdinwv","ihmn","zejwfmawgbury","pbkrrbitsmk","dfgzqvhkdmifrhiz","fqnchfgscfma","hjm","qproqyedlkjlvyvvux","vaxsatg","qvtgsihjukjwgrjt","xmiphybzka","xwelvoaxtfupim","vgolhynte","kprqzdtlgrwsh","heicpbduwmxmgb","zuotbdri","soyahnqfpnbogp","syuz","ddol","jtmdenvkszcvjjjd","cmhhorzoodyjprrynchn","bgcbfebncbxojbsolpnf","basihr","kebcpdtnsamnsdl","itapyzijteqmnisqtkrz","yuhbwijcwpquqwpcimwo","fitnhhjkcqf","dtnklsxnozzqwg","naiqbzek","zhixurittyxibtvntmk","wxyirtbpqlavpjnda","mzzax","ilark","qczkpvsf","wiivqskspghw","jytsywmzvr","gimbnqlxbskyi","sijtdmsxltpudjm","vavfc","yxvjcyuoieziubljya","nksqxwbvkermdi","ntjewyrknnxwbu","cxsxj","mabxkuymkfnbnkzvnlly","ucsuko","xndz","ntydiu","mefvgnjjeeckfzpusd","jhrnzrxefvhzt","zywnfmglzlskwxfzqwi","lemooxezd","vhqwgb","zogynodssberqov","utgmixlsbzlq","rwtgijzhzvdluehekk","knrdlsbt","huhuvep","zjskz","wyjpfefzsrddya","omszttk","zlpexlivzjauuvm","yjcdwaigzvrhpifxt","eebssw","eoorto","jtcxdsqlowskwz","qnx","jiji","plqdtcbysz","pjxbsgjynfzbpduxbys","kwylhuragmahobiik","rqmvabdpt","somkevtbxew","kuhn","sjidcalngaiit","vrdnnkdxnozdd","gttpoilbpzjmh","tfdonswcwecakjlyfkgi","nydxxs","flcdbzpfnlck","kcamsbysgdzvbmwtrs","utkkoznnexaruwh","yuyu","thfrawwzdib","lzqppm","wgg","lsoolnqqsb","yrgzfestvsjec","ppvqgisjwklgbsbrc","odwmixlobaqvbv","pkrevkhisewa","dtzadhxksjvjwbvx","ijkbicoyki","bhnwraupmybsmmzuq","wrhjlghwoczrod","gfssgfzomflmrsfbnsd","ffmjfrjmek","dtqcmsnntosokt","knswwbkxh","ndyhbjjltxcrlrd","qycbnnqjpsoqnaezwajo","hpbbzkmysinkjbcj","ncdhsisbgljhnne","tfnpuzonldzqkawjwje","mpr","fgsqluwccuwgmhdxp","xdbxkmlgc","kxbulpdwxtjzrdktjyw","vifvtvyzvfglixb","htxza","eincto","ocdwoziouashssyhnwjx","vanjrglvtjzdjwli","hacjgehdmfnihjbjuzvs","guyufg","usqvyzjue","fuafk","anvlvrf","ysfglwbvedoijdoh","qndkgnzzl","afjjgzmcriwfstxf","nwsvolvzrpfl","zuzzhvufbovaice","sqinpwqu","mpgwo","bntin","mzfjvjgllwxn","szjihtpszidsmypfb","pavltglzpqmcny","volfab","aybdzxhbdyiphshgm","nccmtrzn","wwvxsorcotibvhwdduxi","sxjftat","lylynpwxi","qczlb","vjwf","idsgvnqkebpdz","lskhmyehfthunhaq","qsl","mylgfxaougpnesokee","okxubhdsufnq","toee","lqbktkcorvgxmoqdi","phkllsdqzeklglxn","jvhhvqjywuvxfbltgmy","flxiy","xrsqudwkqhgzncbxr","trkjwggglltoqqiyf","ruhmd","sdfyalxirvqbgheat","eahqucerryi","tke","iwnz","zdmtjchciizvimblzm","sxmbqulc","rwdzuuiclwsa","zbanscranspzynw","ghbxozfoljr","hmbbrjudmbbdvkjfzz","kzzsqbwzbyosnmgmv","pxrkzjp","nyqxvpmc","dmmi","arnfmcqajweh","ftxppukgip","jczecfofgtiz","ungxlagbfgpdptajrsua","hfprxgtb","wpkyq","ugawipxzqiakrtujbfe","qkicj","unwbmtfyupyaoz","jmrh","zayqoevwndrzwdrqs","zhzvbvxcmgbvhovgae","bdcq","fqbtoyg","bzqumj","thvbeh","ydcbfmlqryuw","jmvkazyrxzo","pgvgjgcnvsaau","jsgwgnterbaybwfwv","tkxwkr","mvnu","inbduvdqftwa","jou","ldhnfibjuuepb","etptikltbbfs","wkondvtwblscigvtpph","yrzxlhzfzuqdr","uhjsuwhpwqjappxybzah","fylbweukprtgazrzygo","ayykvvmjhtmomfvlpam","sgdugultmgvnig","tnblpufrxzbof","yrihbpnzhizzjqnic","mdwfxkrfw","xiwlrhnrksuyecqabg","sdh"};	
	
	size = sizeof(strs)/sizeof(string);
	dic.assign(strs, strs + size);	
	
	ValidWordAbbr vwa(dic);

	cout << vwa.isUnique("onvdwyxropxsxjet") << endl;

	return 0;
}
