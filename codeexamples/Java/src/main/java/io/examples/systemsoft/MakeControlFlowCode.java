package io.examples.systemsoft;

import com.ibm.bcb.BCBClass;
import com.ibm.bcb.BCBMethod;
import org.objectweb.asm.Type;

import static com.ibm.bcb.BCB.*;

public class MakeControlFlowCode {

    public static  Class<?> defineClassWithIfElseIf() {
        return BCBClass.builder()

                .method(BCBMethod.builder()
                        .name("testTheWest")
                        .returnType(Type.INT_TYPE)
                        .body(
                                ret(constant(100))
                        ).build())
                .method(BCBMethod.builder()
                        .name("testTheSwitchAndIf")
                        .returnType(Type.INT_TYPE)
                        .body(
                                ifStmt(load("a").gt(load("b")),
                                        ret(
                                                load("a")
                                        ),

                                        ifStmt(
                                                load("c").gt(load("e")),
                                                ret(
                                                        load("c")
                                                ),
                                                ret(
                                                        load("e")

                                                )))
                        ).build())
                .build()
                .loadClass();
    }
}
