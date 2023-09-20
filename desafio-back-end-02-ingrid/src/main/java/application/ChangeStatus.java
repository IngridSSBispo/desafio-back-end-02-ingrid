package application;


import application.dto.ChangeStatusDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import infrastructure.ProdutoDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;


public class ChangeStatus extends HttpServlet {
    Gson gson = new GsonBuilder().serializeNulls().create();
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("Metodo get foi disparado!");


    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        try {
            ProdutoDAO produtodao = new ProdutoDAO();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            ChangeStatusDTO changeStatusDTO = gson.fromJson(sb.toString(), ChangeStatusDTO.class);

            produtodao.changeStatus(changeStatusDTO);

        } catch (Exception e){
            writer.println("Parametros invalidos");
            e.printStackTrace();
        }

    }

}


