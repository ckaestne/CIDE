package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MethodDeclarationBody1 extends MethodDeclarationBody {
  public MethodDeclarationBody1(Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public MethodDeclarationBody1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MethodDeclarationBody1(cloneProperties(),firstToken,lastToken);
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
