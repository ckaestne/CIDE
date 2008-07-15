package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement1 extends Statement {
  public Statement1(Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public Statement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement1(cloneProperties(),firstToken,lastToken);
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
