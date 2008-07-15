package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TryStatementEnd1 extends TryStatementEnd {
  public TryStatementEnd1(ArrayList<CatchBlock> catchBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<CatchBlock>("catchBlock", catchBlock)
    }, firstToken, lastToken);
  }
  public TryStatementEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TryStatementEnd1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<CatchBlock> getCatchBlock() {
    return ((PropertyOneOrMore<CatchBlock>)getProperty("catchBlock")).getValue();
  }
}
