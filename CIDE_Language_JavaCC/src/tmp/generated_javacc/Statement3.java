package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement3 extends Statement {
  public Statement3(Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public Statement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement3(cloneProperties(),firstToken,lastToken);
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
