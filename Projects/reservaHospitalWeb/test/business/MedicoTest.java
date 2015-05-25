

/**
 * MedicoTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package business;

    /*
     *  MedicoTest Junit test case
    */

    public class MedicoTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testbuscarSuDisponibilidadHora() throws java.lang.Exception{

        business.MedicoStub stub =
                    new business.MedicoStub();//the default implementation should point to the right endpoint

           business.MedicoStub.BuscarSuDisponibilidadHora buscarSuDisponibilidadHora4=
                                                        (business.MedicoStub.BuscarSuDisponibilidadHora)getTestObject(business.MedicoStub.BuscarSuDisponibilidadHora.class);
                    // TODO : Fill in the buscarSuDisponibilidadHora4 here
                
                        assertNotNull(stub.buscarSuDisponibilidadHora(
                        buscarSuDisponibilidadHora4));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testreservarHoraMedicaControl() throws java.lang.Exception{

        business.MedicoStub stub =
                    new business.MedicoStub();//the default implementation should point to the right endpoint

           business.MedicoStub.ReservarHoraMedicaControl reservarHoraMedicaControl6=
                                                        (business.MedicoStub.ReservarHoraMedicaControl)getTestObject(business.MedicoStub.ReservarHoraMedicaControl.class);
                    // TODO : Fill in the reservarHoraMedicaControl6 here
                
                        assertNotNull(stub.reservarHoraMedicaControl(
                        reservarHoraMedicaControl6));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    