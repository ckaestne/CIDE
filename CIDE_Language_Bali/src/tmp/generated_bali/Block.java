package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Block extends GenASTNode {
  public Block(ASTStringNode findblockend, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findblockend", findblockend)
    }, firstToken, lastToken);
  }
  public Block(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Block(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindblockend() {
    return ((PropertyOne<ASTStringNode>)getProperty("findblockend")).getValue();
  }
}
