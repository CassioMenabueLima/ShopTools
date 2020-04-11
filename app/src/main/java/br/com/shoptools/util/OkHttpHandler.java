package br.com.shoptools.util;

import android.os.AsyncTask;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


//192.168.10.100/iot/insert.php?param1=4&param2=2&param3=XXX&param4=100       celular
// 192.168.10.100/iot/consulta.php?param1=4&param2=TEMPERATURA-D6             node-MCU
//192.168.10.100/iot/update.php?param1=1&param2=0                             celular

/**
 * Created by alexandre florentino on 07/06/19.
 */
public class OkHttpHandler extends AsyncTask<Void, Void, String> {
    private final String iotUrl = "http://192.168.15.19:8289/rest/";
    private String iotParam = "";

    OkHttpClient httpClient = new OkHttpClient();

    @Override
    protected String doInBackground(Void... params) {

        Request.Builder builder = new Request.Builder();
        builder.url(iotUrl.trim() + iotParam.trim());

        Request request = builder.build();

        try {

            Response response = httpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.e("kanemaki", "erro");
        }

        return null;

    }

    public String getIotParam() {
        return iotParam;
    }

    public void setIotParam(String iotParam) {
        this.iotParam = iotParam;
    }

}
