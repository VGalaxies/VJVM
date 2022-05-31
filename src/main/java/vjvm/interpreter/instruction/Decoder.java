package vjvm.interpreter.instruction;

import java.util.function.BiFunction;

import lombok.var;
import vjvm.interpreter.instruction.constants.LDCX;
import vjvm.interpreter.instruction.constants.NOP;
import vjvm.interpreter.instruction.constants.XCONST_Y;
import vjvm.interpreter.instruction.constants.XPUSH;
import vjvm.interpreter.instruction.loads.XLOAD;
import vjvm.interpreter.instruction.math.IINC;
import vjvm.interpreter.instruction.math.LIOPR;
import vjvm.interpreter.instruction.math.XNEG;
import vjvm.interpreter.instruction.math.XOPR;
import vjvm.interpreter.instruction.references.INVOKESTATIC;
import vjvm.interpreter.instruction.reserved.BREAKPOINT;
import vjvm.interpreter.instruction.control.XRETURN;
import vjvm.interpreter.instruction.stack.DUPX;
import vjvm.interpreter.instruction.stack.DUPX_XY;
import vjvm.interpreter.instruction.stack.POPX;
import vjvm.interpreter.instruction.stack.SWAP;
import vjvm.interpreter.instruction.stores.XSTORE;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.utils.UnimplementedInstructionError;

public class Decoder {

  public static Instruction decode(ProgramCounter pc, MethodInfo method) {
    var opcode = Byte.toUnsignedInt(pc.byte_());
    if (Decoder.decodeTable[opcode] == null) {
      throw new UnimplementedInstructionError(opcode);
    }

    return Decoder.decodeTable[opcode].apply(pc, method);
  }

  @SafeVarargs
  static BiFunction<ProgramCounter, MethodInfo, Instruction>[] of(
      BiFunction<ProgramCounter, MethodInfo, Instruction>... elem) {
    return elem;
  }

  static final BiFunction<ProgramCounter, MethodInfo, Instruction>[] decodeTable = of(
  // @formatter:off
      /* 0x00 */  NOP::NOP, null, XCONST_Y::ICONST_M1, XCONST_Y::ICONST_0,
      /* 0x04 */  XCONST_Y::ICONST_1, XCONST_Y::ICONST_2, XCONST_Y::ICONST_3, XCONST_Y::ICONST_4,
      /* 0x08 */  XCONST_Y::ICONST_5, XCONST_Y::LCONST_0, XCONST_Y::LCONST_1, XCONST_Y::FCONST_0,
      /* 0x0c */  XCONST_Y::FCONST_1, XCONST_Y::FCONST_2, XCONST_Y::DCONST_0, XCONST_Y::DCONST_1,
      /* 0x10 */  XPUSH::BIPUSH, XPUSH::SIPUSH, LDCX::LDC, LDCX::LDC_W,
      /* 0x14 */  LDCX::LDC2_W, XLOAD::ILOAD, XLOAD::LLOAD, XLOAD::FLOAD,
      /* 0x18 */  XLOAD::DLOAD, null, XLOAD::ILOAD_0, XLOAD::ILOAD_1,
      /* 0x1c */  XLOAD::ILOAD_2, XLOAD::ILOAD_3, XLOAD::LLOAD_0, XLOAD::LLOAD_1,
      /* 0x20 */  XLOAD::LLOAD_2, XLOAD::LLOAD_3, XLOAD::FLOAD_0, XLOAD::FLOAD_1,
      /* 0x24 */  XLOAD::FLOAD_2, XLOAD::FLOAD_3, XLOAD::DLOAD_0, XLOAD::DLOAD_1,
      /* 0x28 */  XLOAD::DLOAD_2, XLOAD::DLOAD_3, null, null,
      /* 0x2c */  null, null, null, null,
      /* 0x30 */  null, null, null, null,
      /* 0x34 */  null, null, XSTORE::ISTORE, XSTORE::LSTORE,
      /* 0x38 */  XSTORE::FSTORE, XSTORE::DSTORE, null, XSTORE::ISTORE_0,
      /* 0x3c */  XSTORE::ISTORE_1, XSTORE::ISTORE_2, XSTORE::ISTORE_3, XSTORE::LSTORE_0,
      /* 0x40 */  XSTORE::LSTORE_1, XSTORE::LSTORE_2, XSTORE::LSTORE_3, XSTORE::FSTORE_0,
      /* 0x44 */  XSTORE::FSTORE_1, XSTORE::FSTORE_2, XSTORE::FSTORE_3, XSTORE::DSTORE_0,
      /* 0x48 */  XSTORE::DSTORE_1, XSTORE::DSTORE_2, XSTORE::DSTORE_3, null,
      /* 0x4c */  null, null, null, null,
      /* 0x50 */  null, null, null, null,
      /* 0x54 */  null, null, null, POPX::POP,
      /* 0x58 */  POPX::POP2, DUPX::DUP, DUPX_XY::DUP_X1, DUPX_XY::DUP_X2,
      /* 0x5c */  DUPX::DUP2, DUPX_XY::DUP2_X1, DUPX_XY::DUP2_X2, SWAP::SWAP,
      /* 0x60 */  XOPR::IADD, XOPR::LADD, XOPR::FADD, XOPR::DADD,
      /* 0x64 */  XOPR::ISUB, XOPR::LSUB, XOPR::FSUB, XOPR::DSUB,
      /* 0x68 */  XOPR::IMUL, XOPR::LMUL, XOPR::FMUL, XOPR::DMUL,
      /* 0x6c */  XOPR::IDIV, XOPR::LDIV, XOPR::FDIV, XOPR::DDIV,
      /* 0x70 */  XOPR::IREM, XOPR::LREM, XOPR::FREM, XOPR::DREM,
      /* 0x74 */  XNEG::INEG, XNEG::LNEG, XNEG::FNEG, XNEG::DNEG,
      /* 0x78 */  LIOPR::ISHL, LIOPR::LSHL, LIOPR::ISHR, LIOPR::LSHR,
      /* 0x7c */  LIOPR::IUSHR, LIOPR::LUSHR, LIOPR::IAND, LIOPR::LAND,
      /* 0x80 */  LIOPR::IOR, LIOPR::LOR, LIOPR::IXOR, LIOPR::LXOR,
      /* 0x84 */  IINC::IINC, null, null, null,
      /* 0x88 */  null, null, null, null,
      /* 0x8c */  null, null, null, null,
      /* 0x90 */  null, null, null, null,
      /* 0x94 */  null, null, null, null,
      /* 0x98 */  null, null, null, null,
      /* 0x9c */  null, null, null, null,
      /* 0xa0 */  null, null, null, null,
      /* 0xa4 */  null, null, null, null,
      /* 0xa8 */  null, null, null, null,
      /* 0xac */  null, null, null, null,
      /* 0xb0 */  null, XRETURN::RETURN, null, null,
      /* 0xb4 */  null, null, null, null,
      /* 0xb8 */  INVOKESTATIC::new, null, null, null,
      /* 0xbc */  null, null, null, null,
      /* 0xc0 */  null, null, null, null,
      /* 0xc4 */  null, null, null, null,
      /* 0xc8 */  null, null, BREAKPOINT::new, null,
      /* 0xcc */  null, null, null, null,
      /* 0xd0 */  null, null, null, null,
      /* 0xd4 */  null, null, null, null,
      /* 0xd8 */  null, null, null, null,
      /* 0xdc */  null, null, null, null,
      /* 0xe0 */  null, null, null, null,
      /* 0xe4 */  null, null, null, null,
      /* 0xe8 */  null, null, null, null,
      /* 0xec */  null, null, null, null,
      /* 0xf0 */  null, null, null, null,
      /* 0xf4 */  null, null, null, null,
      /* 0xf8 */  null, null, null, null,
      /* 0xfc */  null, null, null, null
  // @formatter:on
  );

}
