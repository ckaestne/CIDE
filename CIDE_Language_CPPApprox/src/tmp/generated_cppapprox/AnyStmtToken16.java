package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken16 extends AnyStmtToken {
  public AnyStmtToken16(EnumBlock enumBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EnumBlock>("enumBlock", enumBlock)
    }, firstToken, lastToken);
  }
  public AnyStmtToken16(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken16(cloneProperties(),firstToken,lastToken);
  }
  public EnumBlock getEnumBlock() {
    return ((PropertyOne<EnumBlock>)getProperty("enumBlock")).getValue();
  }
}
