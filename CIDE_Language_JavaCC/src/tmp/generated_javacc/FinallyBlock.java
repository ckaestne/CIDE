package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FinallyBlock extends TryStatementEnd {
  public FinallyBlock(Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public FinallyBlock(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FinallyBlock(cloneProperties(),firstToken,lastToken);
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
