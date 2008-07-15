package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken20 extends AnyStmtToken {
  public AnyStmtToken20(BlockAssignment blockAssignment, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BlockAssignment>("blockAssignment", blockAssignment)
    }, firstToken, lastToken);
  }
  public AnyStmtToken20(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken20(cloneProperties(),firstToken,lastToken);
  }
  public BlockAssignment getBlockAssignment() {
    return ((PropertyOne<BlockAssignment>)getProperty("blockAssignment")).getValue();
  }
}
