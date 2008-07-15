package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken18 extends AnyStmtToken {
  public AnyStmtToken18(BlockAssignment blockAssignment, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BlockAssignment>("blockAssignment", blockAssignment)
    }, firstToken, lastToken);
  }
  public AnyStmtToken18(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken18(cloneProperties(),firstToken,lastToken);
  }
  public BlockAssignment getBlockAssignment() {
    return ((PropertyOne<BlockAssignment>)getProperty("blockAssignment")).getValue();
  }
}
