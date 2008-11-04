package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Initializer extends GenASTNode {
  public Initializer(ASTTextNode text504, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text504", text504),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public Initializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Initializer(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText504() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text504")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
