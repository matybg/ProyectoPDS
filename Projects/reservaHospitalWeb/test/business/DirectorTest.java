

/**
 * DirectorTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package business;

    /*
     *  DirectorTest Junit test case
    */

    public class DirectorTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testobtenerMedicos() throws java.lang.Exception{

        business.DirectorStub stub =
                    new business.DirectorStub();//the default implementation should point to the right endpoint

           business.DirectorStub.ObtenerMedicos obtenerMedicos12=
                                                        (business.DirectorStub.ObtenerMedicos)getTestObject(business.DirectorStub.ObtenerMedicos.class);
                    // TODO : Fill in the obtenerMedicos12 here
                
                        assertNotNull(stub.obtenerMedicos(
                        obtenerMedicos12));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testobtenerPorcentajeOcupacionBox() throws java.lang.Exception{

        business.DirectorStub stub =
                    new business.DirectorStub();//the default implementation should point to the right endpoint

           business.DirectorStub.ObtenerPorcentajeOcupacionBox obtenerPorcentajeOcupacionBox14=
                                                        (business.DirectorStub.ObtenerPorcentajeOcupacionBox)getTestObject(business.DirectorStub.ObtenerPorcentajeOcupacionBox.class);
                    // TODO : Fill in the obtenerPorcentajeOcupacionBox14 here
                
                        assertNotNull(stub.obtenerPorcentajeOcupacionBox(
                        obtenerPorcentajeOcupacionBox14));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testobtenerBoxs() throws java.lang.Exception{

        business.DirectorStub stub =
                    new business.DirectorStub();//the default implementation should point to the right endpoint

           business.DirectorStub.ObtenerBoxs obtenerBoxs16=
                                                        (business.DirectorStub.ObtenerBoxs)getTestObject(business.DirectorStub.ObtenerBoxs.class);
                    // TODO : Fill in the obtenerBoxs16 here
                
                        assertNotNull(stub.obtenerBoxs(
                        obtenerBoxs16));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testobtenerMedicosMasSolicitados() throws java.lang.Exception{

        business.DirectorStub stub =
                    new business.DirectorStub();//the default implementation should point to the right endpoint

           business.DirectorStub.ObtenerMedicosMasSolicitados obtenerMedicosMasSolicitados18=
                                                        (business.DirectorStub.ObtenerMedicosMasSolicitados)getTestObject(business.DirectorStub.ObtenerMedicosMasSolicitados.class);
                    // TODO : Fill in the obtenerMedicosMasSolicitados18 here
                
                        assertNotNull(stub.obtenerMedicosMasSolicitados(
                        obtenerMedicosMasSolicitados18));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testobtenerPorcentajeOcupacionMedico() throws java.lang.Exception{

        business.DirectorStub stub =
                    new business.DirectorStub();//the default implementation should point to the right endpoint

           business.DirectorStub.ObtenerPorcentajeOcupacionMedico obtenerPorcentajeOcupacionMedico20=
                                                        (business.DirectorStub.ObtenerPorcentajeOcupacionMedico)getTestObject(business.DirectorStub.ObtenerPorcentajeOcupacionMedico.class);
                    // TODO : Fill in the obtenerPorcentajeOcupacionMedico20 here
                
                        assertNotNull(stub.obtenerPorcentajeOcupacionMedico(
                        obtenerPorcentajeOcupacionMedico20));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testobtenerPacientesMasAtendido() throws java.lang.Exception{

        business.DirectorStub stub =
                    new business.DirectorStub();//the default implementation should point to the right endpoint

           business.DirectorStub.ObtenerPacientesMasAtendido obtenerPacientesMasAtendido22=
                                                        (business.DirectorStub.ObtenerPacientesMasAtendido)getTestObject(business.DirectorStub.ObtenerPacientesMasAtendido.class);
                    // TODO : Fill in the obtenerPacientesMasAtendido22 here
                
                        assertNotNull(stub.obtenerPacientesMasAtendido(
                        obtenerPacientesMasAtendido22));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    