package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ImportStatement extends GenASTNode {
  public ImportStatement(Name name, ASTTextNode text338, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name),
      new PropertyZeroOrOne<ASTTextNode>("text338", text338)
    }, firstToken, lastToken);
  }
  public ImportStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ImportStatement(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public ASTTextNode getText338() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text338")).getValue();
  }
}
