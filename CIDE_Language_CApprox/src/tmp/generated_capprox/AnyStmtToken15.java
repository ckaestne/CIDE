package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken15 extends AnyStmtToken {
  public AnyStmtToken15(EnumBlock enumBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EnumBlock>("enumBlock", enumBlock)
    }, firstToken, lastToken);
  }
  public AnyStmtToken15(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken15(cloneProperties(),firstToken,lastToken);
  }
  public EnumBlock getEnumBlock() {
    return ((PropertyOne<EnumBlock>)getProperty("enumBlock")).getValue();
  }
}
