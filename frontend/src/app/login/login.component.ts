import {Component, OnInit} from '@angular/core';
import {PoDialogService, PoLanguage} from '@po-ui/ng-components';
import {PoPageLogin, PoPageLoginLiterals} from '@po-ui/ng-templates';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  background: string = '';
  logo: string = ''; // https://tomticket-assets.s3.amazonaws.com/logotipo-empresa/3951.png';
  support: string = 'https://zapsolucoes.tomticket.com/helpdesk/login?id=EP03951'
  exceededAttempts: number = 0;
  productName: string = '';
  customLiterals: PoPageLoginLiterals = {
    welcome: '',
  };
  languages: Array<PoLanguage> = [
    {language: 'pt', description: 'Português'}
  ];
  properties: Array<string> = ['hideRememberUser'];

  constructor(private router: Router,
              private poDialog: PoDialogService) {
  }

  ngOnInit(): void {
    this.restore();
  }

  loginSubmit(formData: PoPageLogin) {
    if (this.exceededAttempts <= 0) {
      this.poDialog.alert({
        title: 'Authenticate',
        message: JSON.stringify(formData)
      });
      this.router.navigate(['dashboard']);
    }
  }

  restore() {
    this.exceededAttempts = 0;
    this.productName = 'Controle de Produção';
  }

}
