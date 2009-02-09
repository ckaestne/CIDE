package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Goal extends GenASTNode implements ISourceFile {
  public Goal(ArrayList<TypeDeclaration> typeDeclaration, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<TypeDeclaration>("typeDeclaration", typeDeclaration),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public Goal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Goal(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<TypeDeclaration> getTypeDeclaration() {
    return ((PropertyZeroOrMore<TypeDeclaration>)getProperty("typeDeclaration")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
