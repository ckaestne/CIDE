package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CompilationUnit extends GenASTNode implements ISourceFile {
  public CompilationUnit(ArrayList<TypeDeclaration> typeDeclaration, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<TypeDeclaration>("typeDeclaration", typeDeclaration),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public CompilationUnit(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CompilationUnit(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<TypeDeclaration> getTypeDeclaration() {
    return ((PropertyZeroOrMore<TypeDeclaration>)getProperty("typeDeclaration")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
