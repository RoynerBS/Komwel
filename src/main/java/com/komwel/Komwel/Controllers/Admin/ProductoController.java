package com.komwel.Komwel.Controllers.Admin;

import com.komwel.Komwel.Models.Categoria;
import com.komwel.Komwel.Models.Producto;
import com.komwel.Komwel.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ColeccionRepository coleccionRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private TallaRepository tallaRepository;


    @GetMapping("/admin/productos")
    public String adminProducto(Model model){
        model.addAttribute("productolist", productoRepository.getAllShow() );
        return "producto";
    }

    @GetMapping("/admin/agregar-producto")
    public String agregar_producto(Model model, Model model1, Model model2, Model model3, Model model4) {
        Producto producto=new Producto();

        model.addAttribute("producto",producto);

        model1.addAttribute("categorialist", categoriaRepository.getAll());
        model2.addAttribute("coleccionlist", coleccionRepository.getAll());
        model3.addAttribute("marcalist", marcaRepository.getAll());
        model4.addAttribute("tallalist", tallaRepository.getAll());
        return "agregar_producto";
    }

    @PostMapping("/admin/guardarProducto")
    public String guardarProducto(@ModelAttribute("producto") Producto producto, @RequestParam("file")MultipartFile imagen) {
        int resultado = productoRepository.verificar(producto);
        if(resultado == 0){
            productoRepository.create(producto);

            Producto producto1 = productoRepository.findByName(producto.getNombre());

            if(!imagen.isEmpty()){
                Path directorioImagenes = Paths.get("src//main//resources//static/images");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

                try {
                    byte[] bytesImg = imagen.getBytes();
                    Path rutacompleta = Paths.get(rutaAbsoluta +"//"+ producto1.getId() +".jpg" );
                    Files.write(rutacompleta,bytesImg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return "redirect:/admin/productos";
        }
        else{
            return "redirect:/error";
        }

    }

    @GetMapping("/admin/borrar-producto/{id}")
    public String borrarProducto(@PathVariable("id") long id) {
        productoRepository.deleteById(id);
        return "redirect:/admin/productos";
    }

    @GetMapping("/admin/actualizar-producto/{id}")
    public String actualizar_producto(@PathVariable("id") long id, Model model, Model model1, Model model2, Model model3, Model model4) {
        Producto producto=productoRepository.findById(id);
        model.addAttribute("producto",producto);


        model1.addAttribute("categorialist", categoriaRepository.getAll());
        model2.addAttribute("coleccionlist", coleccionRepository.getAll());
        model3.addAttribute("marcalist", marcaRepository.getAll());
        model4.addAttribute("tallalist", tallaRepository.getAll());


        return "actualizar_producto";
    }

    @PostMapping("/admin/actualizarProducto")
    public String actualizarProducto(@ModelAttribute("producto") Producto producto) {
        productoRepository.update(producto);
        return "redirect:/admin/productos";
    }


}
