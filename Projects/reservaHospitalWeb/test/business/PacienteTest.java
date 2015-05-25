

/**
 * PacienteTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package business;

    /*
     *  PacienteTest Junit test case
    */

    public class PacienteTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testobtenerMedicosDeUnaEspecialidad() throws java.lang.Exception{

        business.PacienteStub stub =
                    new business.PacienteStub();//the default implementation should point to the right endpoint

           business.PacienteStub.ObtenerMedicosDeUnaEspecialidad obtenerMedicosDeUnaEspecialidad8=
                                                        (business.PacienteStub.ObtenerMedicosDeUnaEspecialidad)getTestObject(business.PacienteStub.ObtenerMedicosDeUnaEspecialidad.class);
                    // TODO : Fill in the obtenerMedicosDeUnaEspecialidad8 here
                
                        assertNotNull(stub.obtenerMedicosDeUnaEspecialidad(
                        obtenerMedicosDeUnaEspecialidad8));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testreservarHoraAps() throws java.lang.Exception{

        business.PacienteStub stub =
                    new business.PacienteStub();//the default implementation should point to the right endpoint

           business.PacienteStub.ReservarHoraAps reservarHoraAps10=
                                                        (business.PacienteStub.ReservarHoraAps)getTestObject(business.PacienteStub.ReservarHoraAps.class);
                    // TODO : Fill in the reservarHoraAps10 here
                
                        assertNotNull(stub.reservarHoraAps(
                        reservarHoraAps10));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testobtenerEspecialidad() throws java.lang.Exception{

        business.PacienteStub stub =
                    new business.PacienteStub();//the default implementation should point to the right endpoint

           business.PacienteStub.ObtenerEspecialidad obtenerEspecialidad12=
                                                        (business.PacienteStub.ObtenerEspecialidad)getTestObject(business.PacienteStub.ObtenerEspecialidad.class);
                    // TODO : Fill in the obtenerEspecialidad12 here
                
                        assertNotNull(stub.obtenerEspecialidad(
                        obtenerEspecialidad12));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testbuscarHoraAPS() throws java.lang.Exception{

        business.PacienteStub stub =
                    new business.PacienteStub();//the default implementation should point to the right endpoint

           business.PacienteStub.BuscarHoraAPS buscarHoraAPS14=
                                                        (business.PacienteStub.BuscarHoraAPS)getTestObject(business.PacienteStub.BuscarHoraAPS.class);
                    // TODO : Fill in the buscarHoraAPS14 here
                
                        assertNotNull(stub.buscarHoraAPS(
                        buscarHoraAPS14));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    