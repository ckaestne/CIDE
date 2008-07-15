package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class JavacodeProduction extends GenASTNode {
  public JavacodeProduction(ASTStringNode _javacode, ScanBlock scanBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_javacode", _javacode),
      new PropertyOne<ScanBlock>("scanBlock", scanBlock)
    }, firstToken, lastToken);
  }
  public JavacodeProduction(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new JavacodeProduction(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_javacode() {
    return ((PropertyOne<ASTStringNode>)getProperty("_javacode")).getValue();
  }
  public ScanBlock getScanBlock() {
    return ((PropertyOne<ScanBlock>)getProperty("scanBlock")).getValue();
  }
}
