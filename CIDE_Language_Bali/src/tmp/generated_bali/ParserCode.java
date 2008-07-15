package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ParserCode extends GenASTNode {
  public ParserCode(ASTStringNode _code, Block block, ASTStringNode _code1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_code", _code),
      new PropertyOne<Block>("block", block),
      new PropertyOne<ASTStringNode>("_code1", _code1)
    }, firstToken, lastToken);
  }
  public ParserCode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ParserCode(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_code() {
    return ((PropertyOne<ASTStringNode>)getProperty("_code")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
  public ASTStringNode get_code1() {
    return ((PropertyOne<ASTStringNode>)getProperty("_code1")).getValue();
  }
}
