package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TryStatementEnd extends GenASTNode {
  public TryStatementEnd(ArrayList<CatchBlock> catchBlock, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<CatchBlock>("catchBlock", catchBlock),
      new PropertyZeroOrOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public TryStatementEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TryStatementEnd(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<CatchBlock> getCatchBlock() {
    return ((PropertyOneOrMore<CatchBlock>)getProperty("catchBlock")).getValue();
  }
  public Block getBlock() {
    return ((PropertyZeroOrOne<Block>)getProperty("block")).getValue();
  }
}
