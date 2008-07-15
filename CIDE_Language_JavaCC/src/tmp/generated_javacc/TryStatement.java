package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TryStatement extends GenASTNode {
  public TryStatement(Block block, TryStatementEnd tryStatementEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Block>("block", block),
      new PropertyOne<TryStatementEnd>("tryStatementEnd", tryStatementEnd)
    }, firstToken, lastToken);
  }
  public TryStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TryStatement(cloneProperties(),firstToken,lastToken);
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
  public TryStatementEnd getTryStatementEnd() {
    return ((PropertyOne<TryStatementEnd>)getProperty("tryStatementEnd")).getValue();
  }
}
