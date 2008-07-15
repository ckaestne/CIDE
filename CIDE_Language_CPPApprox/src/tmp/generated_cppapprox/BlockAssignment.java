package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BlockAssignment extends GenASTNode {
  public BlockAssignment(Cast cast, ASTStringNode findendccb, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Cast>("cast", cast),
      new PropertyOne<ASTStringNode>("findendccb", findendccb)
    }, firstToken, lastToken);
  }
  public BlockAssignment(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BlockAssignment(cloneProperties(),firstToken,lastToken);
  }
  public Cast getCast() {
    return ((PropertyZeroOrOne<Cast>)getProperty("cast")).getValue();
  }
  public ASTStringNode getFindendccb() {
    return ((PropertyOne<ASTStringNode>)getProperty("findendccb")).getValue();
  }
}
