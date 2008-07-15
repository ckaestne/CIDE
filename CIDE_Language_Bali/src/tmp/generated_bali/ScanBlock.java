package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ScanBlock extends GenASTNode {
  public ScanBlock(ASTStringNode findblockbegin, ASTStringNode findblockend, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findblockbegin", findblockbegin),
      new PropertyOne<ASTStringNode>("findblockend", findblockend)
    }, firstToken, lastToken);
  }
  public ScanBlock(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ScanBlock(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindblockbegin() {
    return ((PropertyOne<ASTStringNode>)getProperty("findblockbegin")).getValue();
  }
  public ASTStringNode getFindblockend() {
    return ((PropertyOne<ASTStringNode>)getProperty("findblockend")).getValue();
  }
}
